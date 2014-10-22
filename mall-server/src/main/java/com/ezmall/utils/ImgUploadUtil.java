package com.ezmall.utils;

import com.ezmall.model.Goods;
import com.ezmall.utils.image.AverageImageScale;
import com.ezmall.utils.image.ImageHelp;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-21
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public class ImgUploadUtil {
    public static String isSpecImg(HttpServletRequest request, String param) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile(param);
        if (file.getSize() < 0) {
            return ("上传文件不能为空");
        }
        if (!ImageHelp.isValidImageName(file.getOriginalFilename())) {
            return "上传的文件不是图片格式";
        }
        if (file.getSize() > CommonConstrant.IMG_MAX_SIZE) {
            return "文件大小不能超过1M";
        }


        return CommonConstrant.SUCCESS;
    }

    /**
     * 如果图片存在，则判断是否符合规格
     *
     * @param request
     * @param param
     * @return
     */
    public static String isSpecImgIfExist(HttpServletRequest request, String param) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile(param);

        if (file!=null&&ImageHelp.isValidImageName(file.getOriginalFilename())) {
            if (file.getSize() > CommonConstrant.IMG_MAX_SIZE) {
                return "文件大小不能超过100K";
            }
        }


        return CommonConstrant.SUCCESS;
    }


    /**
     * 传输图片
     *
     * @param file        源文件
     * @param destFileUrl 目标文件路径
     * @param maxHeight   图片高度最大值
     * @param maxWidth    图片宽度最低值
     * @return 失败返回 -1，成功返回图片路径
     */
    public static String uploadImage(MultipartFile file, String destFileUrl, Integer maxWidth, Integer maxHeight) {
        try {
        /*获得文件名*/
            String origName = file.getOriginalFilename();
       /* 第一句获得文件名的扩展名,要注意的是：扩展名并不包含"."*/
            String ext = FilenameUtils.getExtension(origName).toLowerCase(
                    Locale.ENGLISH);
            boolean isImage = ImageHelp.isValidImageExt(ext);

            if (isImage) {
                //生成临时文件
                String path = System.getProperty("java.io.tmpdir");
                File tempFile = new File(path, String.valueOf(System
                        .currentTimeMillis()));

                File destFile = new File(destFileUrl);

                //生成临时文件
                file.transferTo(tempFile);
                //将临时文件保存至正式的文件路径
                AverageImageScale.resizeFix(tempFile, destFile, maxWidth, maxHeight);
                //删除临时文件
                tempFile.deleteOnExit();

                //返回文件路径
                return destFile.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";

    }

    public static String uploadImage(MultipartFile file, String destFileUrl) {
        try {
        /*获得文件名*/
            String origName = file.getOriginalFilename();
       /* 第一句获得文件名的扩展名,要注意的是：扩展名并不包含"."*/
            String ext = FilenameUtils.getExtension(origName).toLowerCase(
                    Locale.ENGLISH);
            boolean isImage = ImageHelp.isValidImageExt(ext);

            if (isImage) {

                File destFile = new File(destFileUrl);
                //生成临时文件
                file.transferTo(destFile);

                return destFile.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }


    /**
     * @param request
     * @param param   上传的图片 在REQUEST的参数
     * @return
     */
    public static String uploadGoodsImage(HttpServletRequest request, Goods goods, String param) {
        //文件分割符
        String separator = System.getProperty("file.separator");
        String thirdDomain = goods.getThirdDomain();
        if (StringUtils.isBlank(thirdDomain)) {
            thirdDomain = CommonConstrant.DOMAIN_NAME;
        }

        String fileUrl = getUploadPath() + separator + getGoodsPicUrl(goods);
        System.out.println("上传图片的地址是:" + fileUrl);
        //判断文件夹是否存在
        File dest = new File(fileUrl);

        //建立文件夹
        if (!dest.getParentFile().exists()) {
            createAllFolder(dest.getParent());
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile(param);
        if (ImageHelp.isValidImageName(file.getOriginalFilename())) {
            uploadImage(file, fileUrl);

        } else {
            //图片不存在怎么办
        }

        return CommonConstrant.SUCCESS;

    }

    /**
     * @param request
     * @param username 商品图片
     * @param param    上传的图片 在REQUEST的参数
     * @return
     */
    public static String uploadAdminImage(HttpServletRequest request, String username, String param) {
        //文件分割符
        String separator = System.getProperty("file.separator");

        String fileUrl = getUploadPath() + separator + "admin" + separator + username + separator + "logo.jpeg";
        //判断文件夹是否存在
        File dest = new File(fileUrl);

        //建立文件夹
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile(param);
        if (ImageHelp.isValidImageName(file.getOriginalFilename())) {
            uploadImage(file, fileUrl);

        }

        return CommonConstrant.SUCCESS;

    }

    //处理因为修改品牌导致路径变化
    public static String dealGoodsPicByBrandNo(Goods old, Goods objNew) {
        //文件分割符
        String result = CommonConstrant.MESSAGE_ERROR;
        String separator = System.getProperty("file.separator");
        String oldUrl = getUploadPath() + separator + old.getGoodsPicture();
        String newUrl = getUploadPath() + separator + getGoodsPicUrl(objNew);
        System.out.println("OLD————图片地址:" + oldUrl);
        System.out.println("NEW————图片地址:" + newUrl);
        File dest = new File(newUrl);
        File oldFile = new File(oldUrl);
        //建立文件夹
        if (!dest.getParentFile().exists()) {
            //建立新的文件夹
            createAllFolder(dest.getParent());
        }
        if (oldFile.exists()) {
            try {
                copyFile(oldFile, dest);
                result = CommonConstrant.SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return result;
    }

    public static String getUploadPath() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println("url-------------" + url.getPath());
        String classPath = url.getPath();
//        String rootPath = "/home/wzm/webapps/ezmall/upload";
        String rootPath = "";

        int index = classPath.indexOf("/WEB-INF/classes");
        if (index > 1) {
            // windows下
            if ("\\".equals(File.separator)) {
                rootPath = classPath.substring(1, index);
                rootPath = rootPath.replace("/", "\\");
            }
            //linux下
            if ("/".equals(File.separator)) {
                rootPath = classPath.substring(0, index);
                rootPath = rootPath.replace("\\", "/");
            }
//            rootPath = rootPath + File.separator + "upload";
        } else {

        }
        System.out.println("THE LAST ROOT_PATH=" + rootPath);
        return rootPath;
    }

    public static String getGoodsPicUrl(Goods goods) {
        StringBuffer buffer = new StringBuffer("upload/");
        String thirdDomain = goods.getThirdDomain();
        if (StringUtils.isBlank(thirdDomain)) {
            thirdDomain = CommonConstrant.DOMAIN_NAME;
        }
        buffer.append("goods").append("/").append(thirdDomain).append("/").append(goods.getBrandNo()).append("/").append(goods.getNo()).append(".jpeg");
        return buffer.toString();
    }

    public static String getAdminPicUrl(String username) {
        StringBuffer buffer = new StringBuffer("upload/");
        buffer.append("admin").append("/").append(username).append("/").append("logo.jpeg");
        return buffer.toString();
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    public static void main(String[] args) {
        String url = "/D:/MyWork/kuming/dqf/ezmall/mall-server/target/classes/";
        String rootPath = url.substring(1, url.indexOf("/classes/"));
        System.out.print(rootPath);
    }


    /**
     * 创建最前一个文件夹路径
     *
     * @param fileFolder
     */
    public static void createLastFolder(File fileFolder) {

        File parent = fileFolder.getParentFile();
        if (!parent.exists()) {

            createLastFolder(parent);

        } else {
            fileFolder.mkdir();

        }
    }

    /**
     * 创建所有的文件夹
     *
     * @param fileUrl
     */
    public static void createAllFolder(String fileUrl) {
        File file = new File(fileUrl);
        while (!file.exists()) {
            createLastFolder(file);
            file = new File(fileUrl);
        }

    }

}
