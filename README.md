PHÂN CÔNG NHIỆM VỤ

- Lưu ý : làm bất cứ thành phần nào có thể click đc vd như button, các item trong list view, ...
nói chung thành phần nào có thể setOnClick thì làm lun cái hàm đó, chỉ cẩn làm click vào nó hiện ra cái thông báo gì đó là đc
( vd dùng hàm touch ) để sau này làm backend cho dễ 
- Cách đặt tên : (tên thuộc tính)_(tên chức năng)

![image](https://user-images.githubusercontent.com/66076345/113801245-a255b280-9782-11eb-9293-af0deb567a43.png)
![image](https://user-images.githubusercontent.com/66076345/113801258-a97cc080-9782-11eb-8697-2752efe42e47.png)


		
		
- Cách đặt tên các thuộc tính ( text, list view) 
	* Trong XML : đặt nguyên cái tên đó cộng với chức năng --> (tên thuộc tính)_(tên chức năng hiển thị viết hoa chữ đầu)
		textview_Name
		
		button_GetStarted
	* Khi ánh xạ trong file activity : (tên thuộc tính)(tên chức năng viết hoa chữ đầu )
	
		textviewName
		
		buttonGetStarted

1. DATABASE ( Hiếu)
2. Chuyển giữa các giao diện với nhau trong menu dưới ( Long )
3. FRONT END

	a. Đăng nhập, đăng kí  ( Long )
                - Chỉ làm giao diện, mấy cái chỗ bắt nhập tên hoặc nhập OTP rồi mới cho đăng kí gì đó thì chưa cần cho nhấn thẳng button 
                để chuyển sang giao diện mới lun
 
	b. Home 
        
		- slide (vd black friday,...)
                
		- categories 
                
		- list view hiển thị sản phẩm ( Hiếu )
                

	c. Notification (back-end) -> làm sau cùng 
       
	d. Search
        
		- Recent Search History ( back-end )
                
		- Trending Search 
                
		- Categories 
                
	e. Message ( tạo khung chat back-end ) -> làm sau cùng để xem còn thời gian k
        
	f. Setting ( Đạt )


4. Back-end

a. Bo tròn ảnh 

b. Úp ảnh từ thư viện trong điện thoại ( dùng để up sản phẩn và avatar ) 

	*Bonus : khi chọn ảnh rồi thì cắt góc căn chỉnh rồi mới up lên
c. Chat ( * làm sau cùng )

d. Database 

	- Search ( tìm kiếm trước đó, tìm kiếm nhiều (trending), tìm kiếm tên sản phẩm )
	- Thông tin từng sản phẩm
		* id sản phẩm  : từng sản phẩm có id khác nhau
		* id của shop bán sản phẩm đó ( ID user )
		* Tên sản phẩm
		* Hình sản phẩm ( 1 hình )
		* Giá sản phẩm ( đơn vị $ hết cho đồng bộ )
		* Số sao ( rating bar )
		* Số người đã mua
		* Nơi bán 
		* Thông tin chi tiết, mô tả sản phẩm
		* Đánh giá của người dùng (* làm sau cùng )
		* Mã giảm giá (* làm sau cùng )
		* Sản phẩm tương tự ( * làm sau cùng )
		* Nước sản xuất
	- Thông tin chi tiết sản phẩm
		* ID sản phẩm
		* Size
		* Color
		* Số sản phẩm còn trong kho
		* Số sản phẩm đã bán
	- User
		* ID user
		* Tên user ( vừa là tên shop đại diện bán hàng )
		* Địa chỉ 
		* Sđt
		* Avatar

	- Hóa đơn
		* Số hóa đơn
		* Ngày hóa đơn
		* Mã khách hàng( ID user )
		* Giá
	- CTHD 
		* Số hóa đơn
		* id sản phẩm
		* Số lượng
		
![image](https://user-images.githubusercontent.com/66076345/113800883-eeecbe00-9781-11eb-8c52-be82df64b034.png)
		
