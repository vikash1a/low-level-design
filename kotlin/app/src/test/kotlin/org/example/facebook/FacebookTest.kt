package org.example.facebook

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FacebookTest{
    @Test
    fun `facebook test`(){
        val notificationService = NotificationService()
        val friendService = FriendService()
        val facebook = Facebook(
            authService = AuthService(UserService()),
            friendRequestService = FriendRequestService(notificationService, friendService),
            postService = PostService(notificationService, friendService),
            friendService = friendService
        )

        val userId1 = facebook.signUp("vikash1a","pswd", "vikash1", "sample url")
        val userId2 = facebook.signUp("vikash2a","pswd", "vikash2", "sample url")
        facebook.login("vikash1a","pswd")
        facebook.login("vikash2a","pswd")
        facebook.sendFriendRequest(userId1, userId2)
        facebook.acceptFriendRequest(userId1, userId2)
        assertEquals(1, facebook.getFriends(userId1).size )
        val postId = facebook.createPost(userId1, "first post", mutableListOf())
        facebook.commentPost(postId, "nice post", userId2)
        facebook.likePost(postId)
        val posts = facebook.getNewsFeed(userId1)
        assertNotNull(posts)
    }
}