package top.cyanide.cyanide.web.controller.admin;

import top.cyanide.cyanide.model.domain.Post;
import top.cyanide.cyanide.model.dto.HaloConst;
import top.cyanide.cyanide.service.PostService;
import top.cyanide.cyanide.util.HaloUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

/**
 * @author : RYAN0UP
 * @date : 2018/1/21
 * @version : 1.0
 * description : 备份
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/backup")
public class BackupController {

    @Autowired
    private PostService postService;

    /**
     * 渲染备份页面
     *
     * @param model model
     * @return 模板路径admin/admin_backup
     */
    @GetMapping
    public String backup(){
        return "admin/admin_backup";
    }

    /**
     * 备份数据库
     *
     * @return 重定向到/admin/backup
     */
    @GetMapping(value = "/backupDb")
    public String backupDatabase(){
        String fileName = "db_backup_"+HaloUtil.getStringDate("yyyy_MM_dd_HH_mm_ss")+".sql";
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String savePath = path.getAbsolutePath()+"/backup/database";
            HaloUtil.exportDatabase("localhost","root","123456",savePath,fileName,"testdb");
        }catch (Exception e){
            log.error("未知错误：{0}",e.getMessage());
        }
        return "redirect:/admin/backup";
    }

    /**
     * 备份资源文件 重要
     *
     * @return return
     */
    @GetMapping(value = "/backupRe")
    public String backupResources(){
        return null;
    }

    /**
     * 备份文章，导出markdown文件
     *
     * @return 重定向到/admin/backup
     */
    @GetMapping(value = "/backupPost")
    public String backupPosts(){
        List<Post> posts = postService.findAllPosts(HaloConst.POST_TYPE_POST);
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String savePath = path.getAbsolutePath()+"/backup/posts/posts_backup_"+HaloUtil.getStringDate("yyyy_MM_dd_HH_mm_ss");
            for(Post post : posts){
                HaloUtil.dbToFile(post.getPostContentMd(),savePath,post.getPostTitle()+".md");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/backup";
    }
}
