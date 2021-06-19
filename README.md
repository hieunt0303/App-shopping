PHÂN CÔNG NHIỆM VỤ

- Lưu ý : làm bất cứ thành phần nào có thể click đc vd như button, các item trong list view, ...
nói chung thành phần nào có thể setOnClick thì làm lun cái hàm đó, chỉ cẩn làm click vào nó hiện ra cái thông báo gì đó là đc
( vd dùng hàm touch ) để sau này làm backend cho dễ 


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
		* id của shop bán sản phẩm đó
		* Hình của sản phẩm
		* Tên sản phẩm
		* Giá sản phẩm ( đơn vị $ hết cho đồng bộ )
		* Số sao ( rating bar )
		* Số người đã mua
		* Nơi bán 
		* Thông tin chi tiết, mô tả sản phẩm
		* Số sản phẩm còn trong kho
		* Size
		* Đánh giá của người dùng (* làm sau cùng )
		* Mã giảm giá (* làm sau cùng )
		* Sản phẩm tương tự ( * làm sau cùng )
	- Thông tin shop bán sản phẩm
		* id : từng shop có id khác nhau 
		* Tên shop
		* Địa chỉ shop
		* Số người đã mua tất cả sản phẩm
		* Số sản phẩm ( list hết sản phẩm bằng *id )
	- Thông tin người mua
		* id người mua
		* Tên người mua
		* Địa chỉ
		* Sđt
		* Ngày sinh

	- Hóa đơn
		* Số hóa đơn
		* Ngày hóa đơn
		* Mã khách hàng
		* Giá
	- CTHD 
		* Số hóa đơn
		* id sản phẩm
		* Số lượng
		
