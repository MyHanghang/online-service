package online.controller;

import online.mybatis.mapper.ExamUserMapper;
import online.mybatis.mapper.ExamVideoMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamUser;
import online.mybatis.model.ExamUserExcel;
import online.mybatis.model.ExamVideo;
import online.mybatis.model.Tbrecord;
import online.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName b
 * @Description UploadController，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:22
 * @Version V2.0
 */
@Controller
@RequestMapping("/uploadvideo")
public class UploadController extends BaseController {

    @Resource
    private ExamVideoMapper mapper;

    @Resource
    private TbrecordMapper recordMapper;

    @Resource
    private ExamUserMapper examUserMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map uploadFile(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file, @RequestParam(value = "videoTitle",
            required = true) String videoTitle, @RequestParam(value = "videoType", required = true) String videoType) throws Exception {
        System.out.println("进入后台" + file.getOriginalFilename() + " === " + videoTitle + "" + videoType);
        String fileName = file.getOriginalFilename();
        String prefix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        ExamVideo examVideo = new ExamVideo();
        String videoNo = NoUtils.getNo();
        String path = videoNo + prefix;
        Map responseMap = new HashMap();
        FileOutputStream out;
        out = new FileOutputStream("E:/毕业论文/online-service/WebContent/upload/" + path);
        out.write(file.getBytes());
        out.flush();
        out.close();
        try {
            //新增修改学习资源
            HttpSession session = getSession();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            format.parse(DateUtils.getDate());
            examVideo.setId(UuidUtil.get32UUID());
            examVideo.setPath(path);
            examVideo.setTitile(videoTitle);
            examVideo.setType(videoType);
            examVideo.setOprid((String) session.getAttribute("oprId"));
            examVideo.setOprtime(format.parse(DateUtils.getDate()));
            examVideo.setSpare1(DateUtils.getDate());
            examVideo.setSpare2("0");
            System.out.println(examVideo);
            mapper.insertSelective(examVideo);

            //操作记录
            Tbrecord record = new Tbrecord();
            record.setId(UuidUtil.get32UUID());
            record.setOprid((String) session.getAttribute("oprId"));
            record.setOprtime(DateUtils.getDate());
            record.setOprmemo(session.getAttribute("oprId") + "发布了题目为：【 " + examVideo.getTitile() + "】 的视频资源~");
            recordMapper.insert(record);
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/uploadExcel")
    @ResponseBody
    public Map uploadFileExcel(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        System.out.println("进入后台" + file.getOriginalFilename() + " === ");
        String rootPath = "E:/毕业论文/online-service/WebContent/excel/";
        String fileName = file.getOriginalFilename();
        String excelPath = rootPath + fileName;
        Map responseMap = new HashMap();
        FileOutputStream out;
        out = new FileOutputStream(excelPath);
        out.write(file.getBytes());
        out.flush();
        out.close();
        try {
            List<String[]> excelData = POIUtil.readExcel(new File(excelPath));
            //数据库操作
            ExamUserExcel excel = new ExamUserExcel();
            for (String[] string : excelData) {
                excel.setId(string[0]);
                excel.setUsername(string[1]);
                excel.setUserpass(MD5.md5(string[2]));
                excel.setUsertype(string[3]);
                excel.setUsergroup(string[4]);
                excel.setUserphone(string[5]);
                excel.setUseraddr(string[6]);
                excel.setSpare3(string[7]);
                examUserMapper.insertExcel(excel);
            }
            responseMap.put("status", Dict.STATUS_00);
        } catch (
                Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        return responseMap;
    }
}