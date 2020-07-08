package com.jumi.web.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jumi.web.dao.NoticeAttachDAO;
import com.jumi.web.domain.NoticeAttachVO;

@Component
public class FileCheckTask {
	
	@Autowired
	private NoticeAttachDAO attachdao;	
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() throws Exception{
		List<NoticeAttachVO> fileList = attachdao.getOldFiles();
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid()+"-"+vo.getFileName()))
				.collect(Collectors.toList());
		fileList.stream().filter(vo -> vo.getFileType() == true)
		.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_"+vo.getUuid()+"-"+vo.getFileName()))
		.forEach(p -> fileListPaths.add(p));
		
		fileListPaths.forEach(p -> System.out.println("======================"+p));
		
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false	);
		
		for(File file:removeFiles) {
			file.delete();
		}
	}
}
