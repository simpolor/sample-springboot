package io.simpolor.upload.component;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class FileComponent {

    private String filePath;
    private String filePath2;

    @PostConstruct
    public void init() throws Exception{

        // spring framework의 Resource를 이용하여 포스트의 절대 경로를 구함.
       /* ClassPathResource cpr = new ClassPathResource("/");
        File resourceFile = cpr.getFile();
        filePath = resourceFile.getParent();*/
        System.out.println("test");
    }

    public String getFilePath(){
        try{
            ClassPathResource cpr = new ClassPathResource("/");
            File resourceFile = cpr.getFile();
            filePath = resourceFile.getParent();

            File temp = resourceFile.getParentFile();
            filePath2 = temp.getParent();

            filePath2 = filePath2.concat(File.separator)
                            .concat("src")
                            .concat(File.separator)
                            .concat("webapp")
                            .concat(File.separator)
                            .concat("upload");

            System.out.println("filePath : "+filePath);
            System.out.println("filePath2 : "+filePath2);

            return this.filePath;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }


    /*public List<Post> loadAllPost(){

        // Post의 절대경로를 통해 모든 Post 목록을 불러옴.
        List<Post> posts = new ArrayList<>();

        File postFiles = new File(postPath);
        for(File file : postFiles.listFiles()){
            // posts.add(Post.of(file.getName(), file.getPath()));
            posts.add(readPost(file));
        }

        return posts;
    }*/
}
