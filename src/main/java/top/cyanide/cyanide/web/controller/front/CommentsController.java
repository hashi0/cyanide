package top.cyanide.cyanide.web.controller.front;

import top.cyanide.cyanide.model.domain.Comment;
import top.cyanide.cyanide.model.domain.Post;
import top.cyanide.cyanide.model.dto.HaloConst;
import top.cyanide.cyanide.service.CommentService;
import top.cyanide.cyanide.service.MailService;
import top.cyanide.cyanide.service.PostService;
import top.cyanide.cyanide.service.UserService;
import top.cyanide.cyanide.util.HaloUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author : RYAN0UP
 * @version : 1.0
 * @date : 2018/4/26
 */
@Slf4j
@Controller
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * 获取文章的评论
     *
     * @param postId postId 文章编号
     * @return List<Comment>集合</>
     */
    @GetMapping(value = "/getComment/{postId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Comment> getComment(@PathVariable Long postId){
        Optional<Post> post = postService.findByPostId(postId);
        Sort sort = new Sort(Sort.Direction.DESC,"commentDate");
        Pageable pageable = new PageRequest(0,10,sort);
        List<Comment> comments = commentService.findCommentsByPostAndCommentStatus(post.get(),pageable,2).getContent();
        if(null==comments){
            return null;
        }
        return comments;
    }

    /**
     * 提交新评论
     *
     * @param comment comment实体
     * @param post post实体
     * @param request request
     * @return true：评论成功，false：评论失败
     */
    @PostMapping(value = "/newComment")
    @ResponseBody
    public boolean newComment(@ModelAttribute("comment") Comment comment,
                              @ModelAttribute("post") Post post,
                              HttpServletRequest request){
        if(StringUtils.isBlank(comment.getCommentAuthor())){
            comment.setCommentAuthor("小猪佩琪");
        }
        comment.setCommentAuthorEmail(comment.getCommentAuthorEmail().toLowerCase());
        comment.setPost(post);
        comment.setCommentDate(new Date());
        comment.setCommentAuthorIp(HaloUtil.getIpAddr(request));
        comment.setIsAdmin(0);
        commentService.saveByComment(comment);

        if(StringUtils.equals(HaloConst.OPTIONS.get("smtp_email_enable"),"true") && StringUtils.equals(HaloConst.OPTIONS.get("new_comment_notice"),"true")){
            try {
                //发送邮件到博主
                Map<String,Object> map = new HashMap<>();
                map.put("author",userService.findUser().getUserDisplayName());
                map.put("pageName",postService.findByPostId(post.getPostId()).get().getPostTitle());
                map.put("blogUrl",HaloConst.OPTIONS.get("blog_url"));
                map.put("visitor",comment.getCommentAuthor());
                map.put("commentContent",comment.getCommentContent());
                mailService.sendTemplateMail(userService.findUser().getUserEmail(),"有新的评论",map,"common/mail/mail_admin.ftl");
            }catch (Exception e){
                log.error("邮件服务器未配置：{0}",e.getMessage());
            }
        }
        return true;
    }
}
