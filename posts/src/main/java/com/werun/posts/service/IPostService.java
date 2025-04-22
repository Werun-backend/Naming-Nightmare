package com.werun.posts.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
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
     * 查询个人帖子
     *
      * @param pageModel
     * @return
     */
    public Result readPostByAuthor(PageModel pageModel);

    /**
     * 用帖子id查询帖子
     *
     * @return
     */
    public Result readPostByPostId(Long postId);

    /**
     * 用标签查询帖子
     *
     * @param LabelContent
     * @return
     */
    public Result readPostByLabel(String LabelContent,PageModel pageModel);

    /**
     * 用帖子内容查询帖子
     *
     * @param PostContent
     * @param pageModel
     * @return
     */
    public Result readPostByContent(String PostContent,PageModel pageModel);


    /**
     * 编辑帖子
     *
     * @param postId
     * @return
     */
    public Result updatePost(Long postId,String typeName,String newParam);

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
