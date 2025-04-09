import WallService.add
import WallService.createComment
import WallService.posts
import WallService.postsAny
import WallService.sizeOfPosts
import WallService.update


fun main() {
    val postIni = Post(0, likes = Post.Like(0, false, false, false))

    add(postIni)
    add(postIni)
    add(postIni)
    add(postIni)
    add(postIni)

    println("posts size is = ${sizeOfPosts()}")
    postsAny(1)

    val sample = Post(3, copyright = "Super", likes = Post.Like(500, canLike = true))
    println(update(sample))

    val comment: Comment?
    comment = Comment(25, 55, " This is the desired object")
    println("posts size is = ${sizeOfPosts()}")

    println(createComment(1, comment))
    println(createComment(0, comment))  // PostNotFoundException

}

class PostNotFoundException(message: String) : RuntimeException(message)

data class Post(
    var id: Int = 0,
    val ownerId: Int = 0,
    val formId: Int = 0,
    val createdBy: Int = 0,
    val text: String = "Hello!",
    val copyright: String = "protected",
    val postType: String = "brief story",
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val likes: Like = Like()
) {
    init {
        println("\nInit: New Post instance created, id = $id")
    }

    fun showDataPost() {
        println(
            "Данные объекта post: id = $id, ownerId = $ownerId, formId = $formId," +
                    " copyright = $copyright, canPin = $canPin, $text, likes = ${likes.count}, canLike = ${likes.canLike}"
        )
    }

    class Like(
        val count: Int = 0,
        val userLikes: Boolean = false,
        val canLike: Boolean = false,
        val canPublish: Boolean = false
    )
}

data class Comment(
    var comId: Int = 1,
    val comFromId: Int = 1,
    val comText: String = " No comments"
)

object WallService {
    var posts = emptyArray<Post>()
    private var number: Int = 0
    private var comments = emptyArray<Comment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        for (i in posts.indices) {
            if (postId == posts[i].id) {
                comment.comId = postId
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException("An object with ID $postId was not found")
    }

    fun add(post: Post): Post {
        val p = post.copy(id = ++number)
        posts += p
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (i in posts.indices) {
            if (post.id == posts[i].id) {
                posts[i] = post
                return true
            }
        }
        return false
    }

    fun sizeOfPosts(): Int {
        return posts.size
    }

    fun postsAny(index: Int) {
        println("Element $index of posts:")
        posts[index].showDataPost()
    }

    fun resetNumber() {
        number = 0
    }

    fun resetPosts() {
        posts = emptyArray<Post>()
    }
}