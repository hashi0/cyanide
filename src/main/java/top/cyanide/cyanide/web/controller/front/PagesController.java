package top.cyanide.cyanide.web.controller.front;

import top.cyanide.cyanide.model.domain.Gallery;
import top.cyanide.cyanide.model.domain.Link;
import top.cyanide.cyanide.model.domain.Post;
import top.cyanide.cyanide.model.dto.HaloConst;
import top.cyanide.cyanide.service.GalleryService;
import top.cyanide.cyanide.service.LinkService;
import top.cyanide.cyanide.service.PostService;
import top.cyanide.cyanide.web.controller.core.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author : RYAN0UP
 * @version : 1.0
 * @date : 2018/4/26
 */
@Controller
public class PagesController extends BaseController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private PostService postService;

    @Autowired
    private LinkService linkService;

    /**
     * 跳转到图库页面
     *
     * @return 模板路径/themes/{theme}/gallery
     */
    @GetMapping(value = "/gallery")
    public String gallery(Model model){
        List<Gallery> galleries = galleryService.findAllGalleries();
        model.addAttribute("galleries",galleries);
        return this.render("gallery");
    }

    /**
     * 友情链接
     *
     * @param model model
     * @return 模板路径/themes/{theme}/links
     */
    @GetMapping(value = "/links")
    public String links(Model model){
        //所有友情链接
        List<Link> links = linkService.findAllLinks();
        model.addAttribute("links",links);
        return this.render("links");
    }

    /**
     * 渲染自定义页面
     *
     * @param postUrl 页面路径
     * @param model model
     * @return 模板路径/themes/{theme}/post
     */
    @GetMapping(value = "/p/{postUrl}")
    public String getPage(@PathVariable(value = "postUrl") String postUrl,Model model){
        Post post = postService.findByPostUrl(postUrl,HaloConst.POST_TYPE_PAGE);
        model.addAttribute("post",post);
        return this.render("post");
    }
}
