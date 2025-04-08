package com.werun.posts.controller;

import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.response.BaseResponse;
import com.werun.posts.server.ILabelService;
import com.werun.posts.server.IPostService;
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
    public BaseResponse createPost(@RequestBody PostDTO postDTO) {
        return iPostService.createPost(postDTO);
    }

    /**
     * 删除帖子
     *
     * @return
     */
    @GetMapping("/deletePost")
    @Operation(summary = "删除帖子", description = "删除帖子")
    public BaseResponse deletePost(@RequestParam Long postId) {
        return iPostService.deletePost(postId);
    }

    /**
     * 查询个人帖子
     *
     * @return
     */
    @GetMapping("/readPostByAuthor")
    @Operation(summary = "查询个人帖子", description = "查询个人帖子")
    public BaseResponse readPostByAuthor(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iPostService.readPostByAuthor(pageModel);
    }

    /**
     * 编辑帖子
     *
     * @return
     */
    @GetMapping("/updatePost")
    @Operation(summary = "编辑帖子", description = "编辑帖子")
    public BaseResponse updatePost(@RequestParam Long postId,@RequestParam String typeName,@RequestParam String newParam) {
        return iPostService.updatePost(postId,typeName,newParam);
    }

    /**
     * 根据postId查询帖子
     *
     * @return
     */
    @GetMapping("/readPostByPostId")
    @Operation(summary = "根据postId查询帖子", description = "根据postId查询帖子")
    public BaseResponse readPostByPostId(@RequestParam Long postId) {
        return iPostService.readPostByPostId(postId);
    }

    /**
     * 新增标签
     *
     * @return
     */
    @GetMapping("/createLabel")
    @Operation(summary = "新增标签", description = "新增标签")
    public BaseResponse createLabel(@RequestParam String labelContext) {
        return iLabelService.createLabel(labelContext);
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @GetMapping("/readAllLabels")
    @Operation(summary = "查询所有标签", description = "查询所有标签")
    public BaseResponse readAllLabels(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return iLabelService.readAllLabels(pageModel);
    }
}
