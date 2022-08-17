package dev.queen.retrofit

data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
)

data class Comments(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var commentbody: String
)