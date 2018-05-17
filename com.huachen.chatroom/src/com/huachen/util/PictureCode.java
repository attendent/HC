package com.huachen.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pictureCode")
public class PictureCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PictureCode() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int width = 120;
		int height = 30;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

		Graphics graphics = bufferedImage.getGraphics();
		// 绘制背景
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);
		// 绘制图框
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width - 1, height - 1);
		// 旋转
		Graphics2D garphics2d = (Graphics2D) bufferedImage.getGraphics();
		garphics2d.setColor(Color.RED);

		// 字体与验证码
		graphics.setFont(new Font("宋体", Font.BOLD, 20));
		String content = "023456789abcdefghijknmopqrstuvwxyz";
		Random random = new Random();
		int x = 10;
		int y = 20;
		String rands = "";
		for (int i = 0; i < 4; i++) {
			// 生成旋转角度-30~30
			double jiaodu = random.nextInt(60) - 30;
			// 将角度转换为弧度
			double theta = jiaodu / 180 * Math.PI;
			int index = random.nextInt(content.length());
			char letter = content.charAt(index);
			garphics2d.rotate(theta, x, y);
			graphics.drawString(letter + "", x, y);
			rands+= letter;
			garphics2d.rotate(theta, x, y);
			x += 30;
		}
		// 绘制干扰线
		graphics.setColor(Color.LIGHT_GRAY);
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 10; i++) {
			x1 = random.nextInt(width);
			y1 = random.nextInt(height);
			x2 = random.nextInt(width);
			y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}
		// 关闭
		graphics.dispose();
		// 输出
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		System.out.println("Code"+rands);
		request.getSession().setAttribute("code",rands);

	}
}
