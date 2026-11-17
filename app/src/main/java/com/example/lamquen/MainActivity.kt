
package com.example.lamquen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.lamquen.model.LuotNhan
import com.example.lamquen.helper.QuyTacPhieu_2311
import com.example.lamquen.helper.chuanHoaTen_2311

class MainActivity : AppCompatActivity() {

    private lateinit var edtTen: EditText
    private lateinit var edtSoLuong: EditText
    private lateinit var txtKetQua: TextView
    private lateinit var txtMaLuot: TextView

    // Lưu lượt hợp lệ cuối cùng
    var luotCuoi_2311: LuotNhan? = null

    // Đếm số lượt nhập hợp lệ
    private var soThuTu = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View
        edtTen = findViewById(R.id.edtTen)
        edtSoLuong = findViewById(R.id.edtSoLuong)
        txtKetQua = findViewById(R.id.txtKetQua)
        txtMaLuot = findViewById(R.id.txtMaLuot)

        val btnKiemTra = findViewById<Button>(R.id.btnKiemTra)
        val btnXoa = findViewById<Button>(R.id.btnXoa)

        // Sự kiện nút Kiểm tra
        btnKiemTra.setOnClickListener {
            kiemTra_2311()
        }

        // Sự kiện nút Xóa
        btnXoa.setOnClickListener {
            xoa_2311()
        }
    }

    fun kiemTra_2311() {

        // Lấy và chuẩn hóa dữ liệu
        val ten = edtTen.text.toString().chuanHoaTen_2311()
        val soLuongText = edtSoLuong.text.toString().trim()

        // Xóa trạng thái cũ
        luotCuoi_2311 = null
        txtKetQua.text = ""
        txtMaLuot.text = ""

        edtTen.error = null
        edtSoLuong.error = null

        // Kiểm tra độ dài tên
        if (ten.length !in 2..40) {
            edtTen.error = "Tên phải từ 2 đến 40 ký tự"
            return
        }

        // Chuyển số an toàn
        val soLuong = soLuongText.toIntOrNull()

        // Kiểm tra số lượng
        if (soLuong == null || soLuong !in 1..9) {
            edtSoLuong.error = "Số lượng phải từ 1 đến 9"
            return
        }

        // Chỉ tăng mã khi dữ liệu hợp lệ
        soThuTu++

        val ma = QuyTacPhieu_2311.taoMaPhieu_2311(soThuTu)

        // Tạo model và lưu lượt hợp lệ
        luotCuoi_2311 = LuotNhan(
            maLuot_2311 = ma,
            hoTen_2311 = ten,
            soLuong_2311 = soLuong
        )

        // Đọc model để hiển thị
        luotCuoi_2311?.let { luot ->

            txtKetQua.text =
                "Xin chào ${luot.hoTen_2311}, số lượng: ${luot.soLuong_2311}"

            txtMaLuot.text = luot.maLuot_2311
        }
    }

    fun xoa_2311() {

        // Xóa dữ liệu nhập
        edtTen.text.clear()
        edtSoLuong.text.clear()

        // Xóa thông báo lỗi
        edtTen.error = null
        edtSoLuong.error = null

        // Xóa kết quả và mã lượt
        txtKetQua.text = ""
        txtMaLuot.text = ""

        // Xóa model cuối
        luotCuoi_2311 = null

        // Không reset soThuTu
    }
}
