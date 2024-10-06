package org.example.facebook

import java.time.LocalDateTime
import java.util.UUID

class Facebook(
    private val authService: AuthService,
    private val friendRequestService: FriendRequestService,
    private val postService: PostService,
    private val friendService: FriendService
) {
    fun signUp(username: String, password: String, name: String, pictureUrl: String)= authService.signUp(username, password, name, pictureUrl)
    fun login(username: String, password: String): String = authService.login(username,password)
    fun updateProfile(username: String, password: String?, name: String?, pictureUrl: String?)= authService.updateProfile(username, password, name, pictureUrl)
    fun sendFriendRequest(fromUser: UUID, toUser: UUID)= friendRequestService.sendFriendRequest(fromUser,toUser)
    fun acceptFriendRequest(fromUser: UUID, toUser: UUID)= friendRequestService.acceptFriendRequest(fromUser,toUser)
    fun declineFriendRequest(fromUser: UUID, toUser: UUID)= friendRequestService.declineFriendRequest(fromUser,toUser)
    fun createPost(userId: UUID, text: String, mediaUrls: MutableList<String>)= postService.createPost(userId, text, mediaUrls)
    fun likePost(postId: UUID)= postService.likePost(postId)
    fun commentPost(postId: UUID, text: String, byUserId: UUID)= postService.commentPost(postId, text, byUserId)
    fun getNewsFeed(userId: UUID) = postService.getNewsFeed(userId)
    fun getFriends(userId: UUID) = friendService.getFriends(userId)
}
class FriendService{
    private val friends: MutableList<Friend> = mutableListOf()
    fun getFriends(userId: UUID): List<Friend> {
        return friends.filter { it.userId == userId }
    }
    fun addFriend(userId: UUID, friendId: UUID){
        friends.add(Friend(userId, friendId))
        friends.add(Friend(friendId, userId))
    }
    fun removeFriend(userId: UUID, friendId: UUID){
        friends.remove(Friend(userId, friendId))
        friends.remove(Friend(friendId, userId))
    }
}
class FriendRequestService(private val notificationService: NotificationService,
    private val friendService: FriendService)
{
    private val friendRequests: MutableList<FriendRequest>  = mutableListOf()
    fun sendFriendRequest(fromUser: UUID, toUser: UUID){
        val friendRequest = FriendRequest(fromUser, toUser, FriendRequestStatus.PENDING)
        friendRequests.add(friendRequest)
        notificationService.notify(toUser, "New Friend Request from $fromUser")
    }
    fun acceptFriendRequest(fromUser: UUID, toUser: UUID){
        val friendRequest = friendRequests.find { it.fromUserId == fromUser && it.toUserId == toUser }!!
        friendRequest.status = FriendRequestStatus.ACCEPTED
        friendService.addFriend(fromUser, toUser)
        notificationService.notify(fromUser, "Friend Request accepted by $toUser")
    }
    fun declineFriendRequest(fromUser: UUID, toUser: UUID){
        val friendRequest = friendRequests.find { it.fromUserId == fromUser && it.toUserId == toUser }!!
        friendRequest.status = FriendRequestStatus.DECLINED
    }
}
class NotificationService{
    private val notifications: MutableList<Notification> = mutableListOf()
    fun notify(toUser: UUID, message: String){
        val notification = Notification(UUID.randomUUID(), toUser, message)
        println("Notified to ${toUser} that $message")
        notifications.add(notification)
    }
}
class AuthService(private val userService: UserService){
    fun signUp(username: String, password: String, name: String, pictureUrl: String): UUID{
        return userService.createUser(username, password, name, pictureUrl)
    }
    fun login(username: String, password: String): String{
        val user = userService.getUser(username, password)
        return generateToken(username)
    }
    fun updateProfile(username: String, password: String?, name: String?, pictureUrl: String?){
        return userService.updateUser(username, password, name, pictureUrl)
    }
    private fun generateToken(username: String): String{
        // validity 1 hour
        return ""
    }
}
class UserService{
    private val users: MutableList<User> = mutableListOf()
    fun createUser(username: String, password: String, name: String, pictureUrl: String): UUID{
        val user = User(UUID.randomUUID(), username, password, name, pictureUrl)
        users.add(user)
        return user.id
    }
    fun updateUser(username: String, password: String?, name: String?, pictureUrl: String?){
        val user = users.find { it.username == username }!!
        password?.let { user.password = it }
        name?.let { user.name = it }
        pictureUrl?.let { user.pictureUrl = it }
    }
    fun getUser(username: String, password: String): User{
        return users.find { it.username == username && it.password == password }!!
    }
}
class PostService(
    private val notificationService: NotificationService,
    private val friendService: FriendService)
{
    private val posts: MutableList<Post> = mutableListOf()
    fun createPost(userId: UUID, text: String, mediaUrls: MutableList<String>): UUID{
        val postId = UUID.randomUUID()
        posts.add(Post(postId,userId, text, mediaUrls))
        return postId
    }
    fun likePost(postId: UUID){
        val post = posts.find { it.id == postId }!!
        post.likeCount += 1
        notificationService.notify(post.userId, "New Like on your post:$postId")
    }
    fun commentPost(postId: UUID, text: String, byUserId: UUID){
        val post = posts.find { it.id == postId }!!
        post.comments.add(Comment(UUID.randomUUID(),text, byUserId))
        notificationService.notify(post.userId, "New Comment on your post:$postId by:$byUserId")
    }
    fun getNewsFeed(userId: UUID): List<Post> {
        val friends = friendService.getFriends(userId)
        return posts.filter { it.userId in friends.map { it.userId } }.sortedByDescending { it.createdTime  }
    }
}
data class User(
    val id: UUID,
    val username: String,
    var password: String,
    var name: String,
    var pictureUrl: String
)
data class FriendRequest(
    val fromUserId: UUID,
    val toUserId: UUID,
    var status: FriendRequestStatus
)
enum class FriendRequestStatus{
    PENDING,
    ACCEPTED,
    DECLINED
}
data class Friend(
    val userId: UUID,
    val friendId: UUID
)
data class Notification(
    val id: UUID,
    val toUserId: UUID,
    val message: String
)
data class Post(
    val id: UUID,
    val userId: UUID,
    val text: String,
    val mediaUrls: MutableList<String>,
    var likeCount: Int = 0,
    val comments: MutableList<Comment> = mutableListOf(),
    val createdTime : LocalDateTime = LocalDateTime.now()
)
data class Comment(
    val id: UUID,
    val text: String,
    val byUserId: UUID
)

/*
signUp(user)
login(username, password)
update(user)

sendFriendRequest(fromUser, toUser)
respondFriendRequest(fronUser, toUser, status)

viewFriends(userId)
like(like)
comment(comment)

viewNewsFeed(userId)

controlAccess()
notifications()

users - id, username, password, pictureUrl, name
friendRequests - fromUser, toUser, status (accepted, declined, pending)
friends - userId, friendUserId
posts- id, text, list<image>, list<video>, list<like>, list<comment>
like - postId, userId
comment - postId, text
 */