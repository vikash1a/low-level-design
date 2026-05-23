## Feature
1. User is able to signup
2. User is able to create group
3. User is able to add member to group
4. User is able to add expense
5. User is able view dues
6. User is able to settle dues

## Model
- User
    - id
    - name
- Group
    - id
    - name
- UserGroupMap
    - id
    - userId
    - groupId
- Expense
     - id
     - amount
     - paidUserId
- Share
    - id
    - expenseId
    - userId
    - amount
- Due
    - id
    - userId
    - groupId
    - toUserId
    - amount
     

## Service
- signup(user) : id
- createGroup(group) : id
- addUser(userId, groupId): id
- addExpense(expense): id
- getDueAmount(userId, groupId): amount




