package com.image.imagemagick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class Imagemagick {

	@SuppressWarnings("deprecation")
	public void convert(String imgPath,String extension,HttpServletRequest request){
		//console call
         String command=request.getRealPath("/")+"ImageMagick-7.0.5-4-portable-Q16-x64\\convert.exe -resize 72x56 "+ imgPath + " " +imgPath.substring(0, imgPath.length()-5) +"_icon."+extension;
         System.out.println(command);
         Process proc;
         try {
				Runtime runtime = Runtime.getRuntime();
				proc = runtime.exec(command);
				proc.getOutputStream().close();
				InputStream inputstream = proc.getInputStream();
				InputStreamReader inputstreamreader = new InputStreamReader(
						inputstream);
				BufferedReader bufferedreader = new BufferedReader(
						inputstreamreader);
				String line;
				while ((line = bufferedreader.readLine()) != null) {
					if (line.contains("Invalid")) {
						System.out.println(line);
						break;
					}
					System.out.println(line);
				}
				//request.getSession().setAttribute("icon", "../themes/ProfileImages/"+SessionManagement.getUserName()+"_wmark.jpeg");
				//model.put("msg", "File uploaded successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	}
	
	@SuppressWarnings("deprecation")
	public void watermark(String imgPath,String extension,HttpServletRequest request){
		Process proc; 
		String command= request.getRealPath("/")+"ImageMagick-7.0.5-4-portable-Q16-x64\\convert.exe " + imgPath + " -font Arial -pointsize 10 -draw \"gravity south fill green text 0,12 'JayHind Express' fill white text 1,12 'JayHind Express' \" "+imgPath;
         ////System.out.println(command);
         try {
				Runtime runtime = Runtime.getRuntime();
				proc = runtime.exec(command);
				proc.getOutputStream().close();
				InputStream inputstream = proc.getInputStream();
				InputStreamReader inputstreamreader = new InputStreamReader(
						inputstream);
				BufferedReader bufferedreader = new BufferedReader(
						inputstreamreader);
				String line;
				while ((line = bufferedreader.readLine()) != null) {
					if (line.contains("Invalid")) {
						////System.out.println(line);
						break;
					}
					System.out.println(line);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
