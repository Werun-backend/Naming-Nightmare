package com.werun.posts.controller;

import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.service.ILabelService;
import com.werun.posts.service.IPostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 创建帖子
     *
     * @return
     */
    @PostMapping("/createPost")
    @Operation(summary = "创建帖子", description = "创建帖子")
    public Result createPost(@RequestBody PostDTO postDTO) {
        return iPostService.createPost(postDTO);
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
     * 新增标签
     *
     * @return
     */
    @GetMapping("/createLabel")
    @Operation(summary = "新增标签", description = "新增标签")
    public Result createLabel(@RequestParam String labelContent) {
        return iLabelService.createLabel(labelContent);
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @GetMapping("/readAllLabels")
    @Operation(summary = "查询所有标签", description = "查询所有标签")
    public Result readAllLabels(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iLabelService.readAllLabels(pageModel);
    }
}
