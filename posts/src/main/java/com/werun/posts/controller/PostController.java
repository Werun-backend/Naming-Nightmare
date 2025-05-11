package com.werun.posts.controller;

import com.werun.common.core.request.Result;
import com.werun.posts.DTO.LabelDTO;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.domain.Posts;
import com.werun.posts.mapper.PostsMapper;
import com.werun.posts.service.ILabelService;
import com.werun.posts.service.IPostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@OpenAPIDefinition(
        info = @Info(
                title = "Post Service",
                version = "v1.0",
                description = "CRUD of posts and labels"
        )
)
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    public IPostService iPostService;
    @Autowired
    public ILabelService iLabelService;
    @Autowired
    public PostsMapper postsMapper;

    /**
     * 创建帖子
     *
     * @return
     */
    @PostMapping(value = "/createPost")
    @Operation(summary = "创建帖子", description = "创建帖子")
    public Result createPost(@RequestBody PostDTO postDTO) {
        return iPostService.createPost(postDTO);
    }


    /**
     * 上传图片
     *
     * @param picture
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadPicture")
    @Operation(summary = "上传图片", description = "上传图片")
    public Result uploadPicture(@RequestParam Long postId,@RequestParam MultipartFile picture) throws IOException {
        byte[] pictureData =picture.getBytes();
        return iPostService.uploadPicture(postId,pictureData);
    }

    /**
     * 图片展示
     *
     * @param postId
     * @return
     */
    @GetMapping("/postPicture")
    @Operation(summary = "预览帖子图片", description = "根据帖子ID获取图片")
    public ResponseEntity<byte[]> previewPicture(@RequestParam Long postId) {
        Posts post = postsMapper.selectPostByPostId(postId);
        if (post == null || post.getPicture() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(post.getPicture(), headers, HttpStatus.OK);
    }

    /**
     * 删除帖子
     *
     * @return
     */
    @GetMapping("/deletePost")
    @Operation(summary = "删除帖子", description = "删除帖子")
    public Result deletePost(@RequestParam Long postId) {
        return iPostService.deletePost(postId);
    }

    /**
     * 查询个人帖子
     *
     * @return
     */
    @GetMapping("/readPostByAuthor")
    @Operation(summary = "查询个人帖子", description = "查询个人帖子")
    public Result readPostByAuthor(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.readPostByAuthor(pageModel);
    }

    /**
     * 用标签查询帖子
     *
     * @return
     */
    @GetMapping("/readPostByLabel")
    @Operation(summary = "用标签查询帖子", description = "用标签查询帖子")
    public Result readPostByLabel(@RequestParam String LabelContent,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.readPostByLabel(LabelContent,pageModel);
    }

    /**
     * 用帖子内容查询帖子
     *
     * @return
     */
    @GetMapping("/readPostByContent")
    @Operation(summary = "用帖子内容查询帖子", description = "用帖子内容查询帖子")
    public Result readPostByContent(@RequestParam String PostContent,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.readPostByContent(PostContent,pageModel);
    }

    /**
     * 推送
     *
     * @return
     */
    @GetMapping("/push")
    @Operation(summary = "推送", description = "推送")
    public Result push(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.push(pageModel);
    }

    /**
     * 编辑帖子
     *
     * @return
     */
    @GetMapping("/updatePost")
    @Operation(summary = "编辑帖子", description = "编辑帖子")
    public Result updatePost(@RequestParam Long postId,@RequestParam String typeName,@RequestParam String newParam) {
        return iPostService.updatePost(postId,typeName,newParam);
    }

    /**
     * 根据postId查询帖子
     *
     * @return
     */
    @GetMapping("/readPostByPostId")
    @Operation(summary = "根据postId查询帖子", description = "根据postId查询帖子")
    public Result readPostByPostId(@RequestParam Long postId) {
        return iPostService.readPostByPostId(postId);
    }

    /**
     * 展示所有帖子
     *
     * @return
     */
    @GetMapping("/showAllPosts")
    @Operation(summary = "展示所有帖子", description = "展示所有帖子")
    public Result showAllPosts(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.showAllPosts(pageModel);
    }

    /**
     * 新增标签
     *
     * @return
     */
    //TODO:
    @PostMapping("/createLabel")
    @Operation(summary = "新增标签", description = "新增标签")
    public Result createLabel(@RequestBody LabelDTO labelDTO) {
        return iLabelService.createLabel(labelDTO.getLabelName());
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @GetMapping("/readAllLabels")
    @Operation(summary = "查询所有标签", description = "查询所有标签")
    public Result readAllLabels() {
        return iLabelService.readAllLabels();
    }

//    /**
//     * 给帖子点赞
//     * @param postId
//     * @return
//     */
//    @PostMapping("/like")
//    @Operation(summary = "给帖子点赞", description = "给帖子点赞")
//    public Result<?> likePost(@RequestParam Long postId) {
//        return iPostService.likePost(postId);
//    }
 }
