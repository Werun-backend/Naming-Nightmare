package com.werun.posts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.DTO.PostUpdateDTO;
import com.werun.posts.domain.Posts;
import org.springframework.web.multipart.MultipartFile;

public interface IPostService extends IService<Posts> {
    /**
     * 创建帖子
     *
     * @param postDTO
     * @return
     */
    public Result createPost(PostDTO postDTO);

    /**
     * 上传图片
     * @param picture
     * @return
     */
    public Result uploadPicture(Long postId,byte[] picture);

    /**
     * 删除帖子（假删除）
     *
     * @param postId
     * @return
     */
    public Result deletePost(Long postId);

    /**
     * 展示所有帖子
     *
     * @return
     */
    public Result showAllPosts(PageModel pageModel);

    /**
     * 查询个人帖子
     *
      * @param pageModel
     * @return
     */
    public Result readPostByAuthor(PageModel pageModel);

    /**
     * 条件查询（id、标签、内容）
     *
     * @param postId
     * @param LabelContent
     * @param PostContent
     * @param pageModel
     * @return
     */
    public Result readPostByConditions(Long postId,String LabelContent,String PostContent,PageModel pageModel);

    /**
     * 编辑帖子
     *
     * @param postUpdateDTO
     * @return
     */
    public Result updatePost(PostUpdateDTO postUpdateDTO);

    /**
     * 推送
     *
     * @param pageModel
     * @return
     */
    public Result push(PageModel pageModel);

//    /**
//     * 给帖子点赞
//     *
//     * @param postId
//     */
//    public Result likePost(Long postId);
}
