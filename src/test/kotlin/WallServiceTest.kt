import WallService.createComment
import WallService.posts
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.resetNumber()
        WallService.resetPosts()
    }

    @Test
    fun add_Test_True() {
        println(1)
        val postTest1 = Post(0, likes = Post.Like())
        val postAdded = WallService.add(postTest1)
        var result = false
        if (postAdded.id != 0) result = true
        assertTrue(result)
    }

    @Test
    fun update_True() {
        println(2)
        val postTest2 = Post(0, likes = Post.Like())
        WallService.add(postTest2)
        WallService.add(postTest2)
        WallService.add(postTest2)
        val sampleTest = Post(2, copyright = "Super", likes = Post.Like(500, canLike = true))
        val result: Boolean = WallService.update(sampleTest)
        assertTrue(result)
    }

    @Test
    fun update_False() {
        println(3)
        val postTest3 = Post(0, likes = Post.Like())
        WallService.add(postTest3)
        WallService.add(postTest3)
        WallService.add(postTest3)
        val sample = Post(4, copyright = "Super", likes = Post.Like(500, canLike = true))
        val result: Boolean = WallService.update(sample)
        assertFalse(result)
    }

    @Test
    fun createComment_Accordance(): Unit {
        println(4)
        val postTest4 = Post(0, likes = Post.Like())
        WallService.add(postTest4)
        WallService.add(postTest4)
        WallService.add(postTest4)
        var comment = Comment()
        val commentNew = createComment(3, comment)
        var result = false

        if (commentNew.comId == posts[commentNew.comId-1].id) result = true
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        println(5)
        val comment = Comment()
        createComment(0, comment)
    }
}