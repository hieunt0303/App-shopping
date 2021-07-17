## Project Title
Đồ án làm về app bán sản phẩm
- Mọi người có thể xem tổng quan về UI của bọn mình ở đây [Figma](https://www.figma.com/file/tsnpWDXvmaGcfIRBtQroyj/App?node-id=0%3A1&viewport=490%2C-486%2C0.6055105924606323)

 ![image](https://user-images.githubusercontent.com/66076345/126030808-e243e98c-294b-4f1c-98dc-3a59777d14ac.png)


#### Nếu có lỗi khi clone đồ án về thì làm theo cách sau ( Lưu ý : đồ án làm trên Android Studio )
 - Bạn có thể xem video youtube của mình để biết cách fix 
 
  [![Watch the video](https://i.imgur.com/vKb2F1B.png)](https://www.youtube.com/watch?v=nAidtCXYXks)

 - Nếu đã xem vẫn k hiểu thì nói chung bạn cứ tạo 1 cái project mới bằng android studio và xóa thư mục .idea trong đồ án và đổi lại bằng thư mục .idea trong cái bạn mới tạo sau đó nó sẽ có lỗi chỗ gradle.properties gì đó nó sẽ bảo bạn thêm 1 dòng nào đó vào trong thư mục gradle.properties và thế là xong. Chúc bạn thành công !
 - Nhớ đổi lại đường dẫn đến file gradle của bạn trong máy nhá
#### Tổng quan
Sẩn phẩm được nhập và lưu trên firebase gồm data về hình ảnh và chi tiết sản phẩm
Quản lí sản phẩm với quyền admin
#### Các chức năng chính 
- Chat realtime giữa user và admin
- Gửi mail để reset mật khẩu khi quên
- Thêm, xóa, sửa sản phẩm
- Mua hàng, thêm sản phẩm vào giỏ hàng
- Khi mua hàng xong có đánh giá gồm : nội dung đánh giá và số sao 
- Thay đổi avatar bằng cách lấy ảnh từ điện thoại
- Thông báo trên điện thoại mỗi khi mua hàng thành công hoặc khi hàng đang vận chuyển hoặc đang giảm giá
#### Các chức năng bên Admin
- Chat với tất cả User
- Edit sản phẩm, sửa và xóa sản phẩm
- Thống kê số lượng hàng bán được trong tháng
#### Các chức năng bên User
- Xem sản phẩm 
- Lọc sản phẩm theo tên
- Thêm sản phẩm vào giỏ hàng và mua sản phẩm
- Xem thông báo những đơn hàng đã trong quá trình vận chuyển và vận chuyển thành công có thể feedback cho sản phẩm đã mua

## Authors
- [@hieunt0303](https://www.github.com/hieunt0303)

## Documentation
Mọi người có thể vào drive báo cáo đồ án để xem rõ hơn
[Documentation](https://drive.google.com/drive/folders/1napIYfDl9K7lpBkpYVcqZujPGpj5b4Ay?fbclid=IwAR3AeS5ju61qiW19mUGH2GfB7QX5aeobnlJf5i1B4m-ifaKgskP8y9F4_lo)

## Tech Stack
**Client:** SQLite 

**Server:** Firebase

![Untitled Diagram](https://user-images.githubusercontent.com/66076345/125297331-58680400-e351-11eb-98f1-9ac3906cf98d.png)

		
