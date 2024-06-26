package View;

import Model.ChiTietGiay;
import Model.GioHang;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhuyenMai;
import Model.NguoiDung;
import Model.SanPham;
import Repo.HoaDonChoRepo;
import Repo.HoaDonRepo;
import Service.SanPhamChiTietService;
import Service.SanPhamService;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_BanHang extends javax.swing.JPanel {

    SanPhamService sanPhamService = new SanPhamService();
    SanPhamChiTietService ctgService = new SanPhamChiTietService();
    ArrayList<ChiTietGiay> listspct = ctgService.getChiTietSanPham();
    DefaultTableModel model = new DefaultTableModel();
    HoaDonRepo hdRepo = new HoaDonRepo();
    HoaDon hd = new HoaDon();
    HoaDonChoRepo hdcRepo = new HoaDonChoRepo();

    public Form_BanHang() {
        initComponents();

        getGioHang();
        fillTableHoaDonCho();
        loadtable(listspct);
    }

    public void loadtable(ArrayList<ChiTietGiay> ctg) {
        DefaultTableModel model = (DefaultTableModel) tb_HdSp2.getModel();
        model.setRowCount(0);
        for (ChiTietGiay chitietgiay : ctg) {
            Object[] data = new Object[]{
                chitietgiay.getId(),
                chitietgiay.getIdSanPham().getTen(),
                chitietgiay.getSoLuong(),
                chitietgiay.getGiaBan(),
                chitietgiay.getIdDanhMuc().getTen(),
                chitietgiay.getIdChatLieu().getTen(),
                chitietgiay.getIdSize().getKichCo(),
                chitietgiay.getIdNSX().getTen(),
                chitietgiay.getIdDe().getTen(),
                chitietgiay.getIdMauSac().getMauSac()

            };
            model.addRow(data);
        }
        listspct = ctg;
    }

    private void fillTableGioHang() {
        model = (DefaultTableModel) tb_giohang2.getModel();

        // Kiểm tra xem có dòng nào được chọn không
        int row = tb_hoadon2.getSelectedRow();
        if (row >= 0) {
            String maHD = tb_hoadon2.getValueAt(row, 0).toString();
            String idHD = hdcRepo.getIdHoaDonByMa(maHD);
            ArrayList<GioHang> list = hdRepo.getGioHang(idHD);
            model.setRowCount(0);
            for (GioHang hd : list) {
                model.addRow(new Object[]{
                    hd.getIdCTSP(),
                    hd.getTenSanPham(),
                    hd.getSoLuong(),
                    hd.getChatLieu(),
                    hd.getMauSac(),
                    hd.getHang(),
                    hd.getSize(),
                    hd.getDonGia(),
                    hd.getTrangThai() == 1 ? "Đã thanh toán" : "Chờ thanh toán"
                });
            }
        }
    }

    private void fillTableHoaDonCho() {
        model = (DefaultTableModel) tb_hoadon2.getModel();
        ArrayList<HoaDon> list = hdcRepo.getAllHoaDonCho();
        model.setRowCount(0);

        for (HoaDon hd : list) {
            String nguoiDung = DangNhap.nguoiDungs.get(0).getTenNguoiDung();
            String trangThai = hd.getTrangThai() == 1 ? "Đã Thanh Toán" : "Chưa Thanh Toán";
            model.addRow(new Object[]{
                hd.getMa(),
                nguoiDung,
                hd.getIdKhachHang() == null ? "Khách lẻ" : hd.getIdKhachHang(),
                hd.getIdKhuyenMai() == null ? "" : hd.getIdKhuyenMai(),
                hd.getNgayTao(),
                trangThai
            });
        }
    }

    private void getGioHang() {
        model = (DefaultTableModel) tb_giohang2.getModel();

        int row = tb_hoadon2.getSelectedRow();
        if (row >= 0) {
            String maHD = tb_hoadon2.getValueAt(row, 0).toString();
            String idHD = hdRepo.getIdHoaDon(maHD);
            ArrayList<GioHang> list = hdRepo.getGioHang(idHD);
            model.setRowCount(0);
            for (GioHang gioHang : list) {
                model.addRow(new Object[]{
                    gioHang.getIdCTSP(),
                    gioHang.getTenSanPham(),
                    gioHang.getSoLuong(),
                    gioHang.getChatLieu(),
                    gioHang.getMauSac(),
                    gioHang.getHang(),
                    gioHang.getSize(),
                    gioHang.getDonGia(),
                    gioHang.getTrangThai() == 1 ? "Đang kinh doanh" : "Ngừng kinh doanh"
                });
            }
        }
    }

    private Boolean checkHDSP() {
        int rowDSSP = tb_HdSp2.getSelectedRow();
        int rowHD = tb_hoadon2.getSelectedRow();
        String idCTSP = tb_HdSp2.getValueAt(rowDSSP, 0).toString();
        String maHD = tb_hoadon2.getValueAt(rowHD, 0).toString();
        String idHD = hdRepo.getIdHoaDon(maHD);
        if (hdcRepo.checkGioHang(idHD, idCTSP) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void clearFormHoaDon1() {
        jlb_maHoaDon2.setText("");
        txt_sdtKH2.setText("");
        txt_tenKH2.setText("");
        txt_thanhtien2.setText("");
        txt_tienKhachTra2.setText("");
        txt_tongTien2.setText("");
        txt_tienThua2.setText("");
        txt_tienKhachTra2.setText("");
        txt_maVoucher2.setText("");
        txt_phanTramGiam2.setText("");
    }

    private void showDetailHDC() {
        int row = tb_hoadon2.getSelectedRow();

        // Kiểm tra xem có hàng nào được chọn không
        if (row != -1) {
            jlb_maHoaDon2.setText(tb_hoadon2.getValueAt(row, 0).toString());
            String maHD = tb_hoadon2.getValueAt(row, 0).toString();
            String idHD = hdcRepo.getIdHoaDonByMa(maHD);
            Double tongtien = hdcRepo.getGiaTienHDCTtoTongTienHDbyIdHD(idHD);
            hdcRepo.capNhatTongTienHoaDon(idHD, tongtien);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            String formattedTongTien = decimalFormat.format(tongtien);
            txt_tongTien2.setText(formattedTongTien);

        } else {
            // Xử lý khi không có hàng nào được chọn, có thể hiển thị thông báo hoặc thực hiện các hành động khác
            System.out.println("Không có hàng nào được chọn!");
        }
    }

    private boolean isValidNumberInput(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void thanhtoan() {
        try {
            // Lấy và làm sạch dữ liệu từ các trường nhập liệu
            String tienKhachTraText = txt_tienKhachTra2.getText().replaceAll(",", "");
            String thanhTienText = txt_thanhtien2.getText().replaceAll(",", "");
            String tongTienText = txt_tongTien2.getText().replaceAll(",", "");

            // Kiểm tra giá trị hợp lệ
            if (!isValidNumberInput(tienKhachTraText) || !isValidNumberInput(thanhTienText)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách trả là số!");
                return; // Thêm return để thoát ra nếu không hợp lệ
            }

            // Chuyển đổi chuỗi sang số thực
            Double tienKhachTra = Double.valueOf(tienKhachTraText);
            Double thanhTien = Double.valueOf(thanhTienText);
            Double tongTien = Double.valueOf(tongTienText);

            // Tính tiền thừa
            Double tienThua = tienKhachTra - thanhTien;

            // Định dạng số cho việc hiển thị
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            String tienThuaFormatted = decimalFormat.format(tienThua);

            if (tienThua < 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách trả không đủ để thanh toán!");
            } else {
                txt_tienThua2.setText(tienThuaFormatted);

                int rowHD = tb_hoadon2.getSelectedRow();
                String maHD = tb_hoadon2.getValueAt(rowHD, 0).toString();
                String idHD = hdcRepo.getIdHoaDonByMa(maHD);

                hdcRepo.updateTrangThaiHoaDon(idHD);
                hdcRepo.ngayThanhToanHoaDon(idHD);
                hdcRepo.updateTienHoaDon(idHD, tongTien, thanhTien);

                clearFormGioHang();
                clearFormHoaDon1();
                fillTableGioHang();
                fillTableHoaDonCho();

                JOptionPane.showMessageDialog(this, "Đã thanh toán thành công!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Định dạng số không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public void clearFormGioHang() {
        ArrayList<HoaDonChiTiet> listCTHD = hdcRepo.getAllHoaDonTatCa();
        int rowHD = tb_hoadon2.getSelectedRow();
        String maHD = tb_hoadon2.getValueAt(rowHD, 0).toString();
        String idHD = hdcRepo.getIdHoaDonByMa(maHD);
        hdcRepo.xoaHoaDonChiTiet(idHD);

        for (HoaDonChiTiet hoaDonChiTiet : listCTHD) {
            String chiTietDep = hoaDonChiTiet.getIdCTG();
            int slnew = hoaDonChiTiet.getSoLuong();
            hdcRepo.capNhatSoLuongChiTietSanPham(chiTietDep, slnew);
        }
        DefaultTableModel model = (DefaultTableModel) tb_giohang2.getModel();
        model.setRowCount(0);
        // fillTableGioHang();
        // fillTableSanPham();
        showDetailHDC();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel_BanHang1 = new javax.swing.JPanel();
        jPanel_TableHD1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_hoadon1 = new javax.swing.JTable();
        jPanel_GioHang1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_giohang1 = new javax.swing.JTable();
        btn_update1 = new javax.swing.JButton();
        btn_delete1 = new javax.swing.JButton();
        btn_clear1 = new javax.swing.JButton();
        jPanel_SanPham1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_HdSp1 = new javax.swing.JTable();
        txt_timkiemsp1 = new javax.swing.JTextField();
        btn_prev1 = new javax.swing.JButton();
        btn_next1 = new javax.swing.JButton();
        btn_timkiemsp1 = new javax.swing.JButton();
        lbl_page1 = new javax.swing.JLabel();
        btn_them1 = new javax.swing.JButton();
        jPanel_HoaDon1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_sdtKH1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_thanhtien1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_tienKhachTra1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_tongTien1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btn_taoHoaDon1 = new javax.swing.JButton();
        jlb_maHoaDon1 = new javax.swing.JLabel();
        btn_thanhToan1 = new javax.swing.JButton();
        btn_huyHoaDon1 = new javax.swing.JButton();
        txt_tienThua1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_tenKH1 = new javax.swing.JTextField();
        btn_tru1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        txt_maVoucher1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_phanTramGiam1 = new javax.swing.JTextField();
        jlb_giamgia1 = new javax.swing.JLabel();
        btn_apDung1 = new javax.swing.JButton();
        btn_huyKM1 = new javax.swing.JButton();
        btn_huyKH1 = new javax.swing.JButton();
        btn_chonKH1 = new javax.swing.JButton();
        btn_chonVoucher1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel_BanHang2 = new javax.swing.JPanel();
        jPanel_TableHD2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_hoadon2 = new javax.swing.JTable();
        jPanel_GioHang2 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_giohang2 = new javax.swing.JTable();
        btn_update2 = new javax.swing.JButton();
        btn_delete2 = new javax.swing.JButton();
        btn_clear2 = new javax.swing.JButton();
        jPanel_SanPham2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_HdSp2 = new javax.swing.JTable();
        txt_timkiemsp2 = new javax.swing.JTextField();
        btn_prev2 = new javax.swing.JButton();
        btn_next2 = new javax.swing.JButton();
        btn_timkiemsp2 = new javax.swing.JButton();
        lbl_page2 = new javax.swing.JLabel();
        btn_them2 = new javax.swing.JButton();
        jPanel_HoaDon2 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txt_sdtKH2 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_thanhtien2 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txt_tienKhachTra2 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_tongTien2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btn_taoHoaDon2 = new javax.swing.JButton();
        jlb_maHoaDon2 = new javax.swing.JLabel();
        btn_thanhToan2 = new javax.swing.JButton();
        btn_huyHoaDon2 = new javax.swing.JButton();
        txt_tienThua2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txt_tenKH2 = new javax.swing.JTextField();
        btn_tru2 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        txt_maVoucher2 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txt_phanTramGiam2 = new javax.swing.JTextField();
        jlb_giamgia2 = new javax.swing.JLabel();
        btn_apDung2 = new javax.swing.JButton();
        btn_huyKM2 = new javax.swing.JButton();
        btn_huyKH2 = new javax.swing.JButton();
        btn_chonKH2 = new javax.swing.JButton();
        btn_chonVoucher2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_BanHang1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_BanHang1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel_TableHD1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_TableHD1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setBackground(new java.awt.Color(255, 153, 102));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Hóa Đơn Chờ");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_hoadon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn ", "Tên Nhân Viên ", "Tên Khách Hàng", "Mã KM", "Ngày Tạo ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hoadon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoadon1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_hoadon1);

        javax.swing.GroupLayout jPanel_TableHD1Layout = new javax.swing.GroupLayout(jPanel_TableHD1);
        jPanel_TableHD1.setLayout(jPanel_TableHD1Layout);
        jPanel_TableHD1Layout.setHorizontalGroup(
            jPanel_TableHD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel_TableHD1Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_TableHD1Layout.setVerticalGroup(
            jPanel_TableHD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TableHD1Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel_GioHang1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_GioHang1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setBackground(new java.awt.Color(255, 153, 102));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Giỏ Hàng");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_giohang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id ctsp", "Tên Sản Phẩm", "Số Lượng ", "Chất Liệu", "Màu Sắc", "Hãng", "Size", "Đơn Giá", "Trạng Thái "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tb_giohang1);

        btn_update1.setText("Chỉnh Sửa");
        btn_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update1ActionPerformed(evt);
            }
        });

        btn_delete1.setText("Xóa");
        btn_delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete1ActionPerformed(evt);
            }
        });

        btn_clear1.setText("Clear");
        btn_clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_GioHang1Layout = new javax.swing.GroupLayout(jPanel_GioHang1);
        jPanel_GioHang1.setLayout(jPanel_GioHang1Layout);
        jPanel_GioHang1Layout.setHorizontalGroup(
            jPanel_GioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GioHang1Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GioHang1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_clear1)
                .addGap(18, 18, 18)
                .addComponent(btn_update1)
                .addGap(47, 47, 47)
                .addComponent(btn_delete1)
                .addGap(110, 110, 110))
        );
        jPanel_GioHang1Layout.setVerticalGroup(
            jPanel_GioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GioHang1Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel_GioHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update1)
                    .addComponent(btn_delete1)
                    .addComponent(btn_clear1))
                .addContainerGap())
        );

        jPanel_SanPham1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_SanPham1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setBackground(new java.awt.Color(255, 153, 102));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Sản Phẩm ");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_HdSp1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Sản Phẩm", "Số Lượng ", "Giá Bán", "Danh Mục ", "Chất liệu", "Size", "Hãng", "Đế", "Màu Sắc "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_HdSp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HdSp1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_HdSp1);

        btn_prev1.setText("<<");
        btn_prev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prev1ActionPerformed(evt);
            }
        });

        btn_next1.setText(">>");
        btn_next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_next1ActionPerformed(evt);
            }
        });

        btn_timkiemsp1.setText("Tìm Kiếm");
        btn_timkiemsp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemsp1ActionPerformed(evt);
            }
        });

        btn_them1.setText("Thêm");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_SanPham1Layout = new javax.swing.GroupLayout(jPanel_SanPham1);
        jPanel_SanPham1.setLayout(jPanel_SanPham1Layout);
        jPanel_SanPham1Layout.setHorizontalGroup(
            jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(jPanel_SanPham1Layout.createSequentialGroup()
                .addGroup(jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SanPham1Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(btn_prev1)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_page1)
                        .addGap(18, 18, 18)
                        .addComponent(btn_next1))
                    .addGroup(jPanel_SanPham1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(26, 26, 26)
                        .addComponent(btn_timkiemsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_timkiemsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, Short.MAX_VALUE))
        );
        jPanel_SanPham1Layout.setVerticalGroup(
            jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SanPham1Layout.createSequentialGroup()
                .addGroup(jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel_SanPham1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_timkiemsp1)
                            .addComponent(txt_timkiemsp1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_them1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel_SanPham1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_prev1)
                    .addComponent(btn_next1)
                    .addComponent(lbl_page1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_BanHang1Layout = new javax.swing.GroupLayout(jPanel_BanHang1);
        jPanel_BanHang1.setLayout(jPanel_BanHang1Layout);
        jPanel_BanHang1Layout.setHorizontalGroup(
            jPanel_BanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BanHang1Layout.createSequentialGroup()
                .addGroup(jPanel_BanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_SanPham1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_TableHD1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_GioHang1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_BanHang1Layout.setVerticalGroup(
            jPanel_BanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BanHang1Layout.createSequentialGroup()
                .addComponent(jPanel_TableHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_GioHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_SanPham1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_HoaDon1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_HoaDon1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setText("Form Hóa Đơn");

        jLabel22.setText("Mã Hóa Đơn :");

        jLabel23.setText("Sđt Khách Hàng:");

        jLabel24.setText("Thành Tiền :");

        txt_thanhtien1.setEditable(false);

        jLabel25.setText("Tiền Khách Trả :");

        jLabel26.setText("Tiền Thừa");

        jLabel27.setText("Tổng Tiền :");

        txt_tongTien1.setEditable(false);

        jLabel28.setText("VND");

        jLabel29.setText("VND");

        jLabel30.setText("VND");

        jLabel31.setText("VND");

        btn_taoHoaDon1.setBackground(new java.awt.Color(102, 255, 102));
        btn_taoHoaDon1.setText("Tạo Hóa Đơn");
        btn_taoHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDon1ActionPerformed(evt);
            }
        });

        btn_thanhToan1.setBackground(new java.awt.Color(102, 255, 102));
        btn_thanhToan1.setText("Thanh Toán");
        btn_thanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToan1ActionPerformed(evt);
            }
        });

        btn_huyHoaDon1.setBackground(new java.awt.Color(255, 102, 102));
        btn_huyHoaDon1.setText("Hủy Hóa Đơn");
        btn_huyHoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyHoaDon1ActionPerformed(evt);
            }
        });

        txt_tienThua1.setEditable(false);

        jLabel32.setText("Tên Khách Hàng : ");

        btn_tru1.setText("Trừ");
        btn_tru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tru1ActionPerformed(evt);
            }
        });

        jLabel33.setText("Mã :");

        jLabel34.setText("Giảm:");

        btn_apDung1.setBackground(new java.awt.Color(204, 255, 204));
        btn_apDung1.setText("Áp Dụng");
        btn_apDung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apDung1ActionPerformed(evt);
            }
        });

        btn_huyKM1.setBackground(new java.awt.Color(255, 204, 204));
        btn_huyKM1.setText("Hủy");
        btn_huyKM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyKM1ActionPerformed(evt);
            }
        });

        btn_huyKH1.setBackground(new java.awt.Color(255, 204, 204));
        btn_huyKH1.setText("Hủy");
        btn_huyKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyKH1ActionPerformed(evt);
            }
        });

        btn_chonKH1.setBackground(new java.awt.Color(204, 255, 204));
        btn_chonKH1.setText("Chọn Khách Hàng");
        btn_chonKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonKH1ActionPerformed(evt);
            }
        });

        btn_chonVoucher1.setBackground(new java.awt.Color(0, 255, 0));
        btn_chonVoucher1.setText("Voucher");
        btn_chonVoucher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonVoucher1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 255, 102));
        jButton2.setText("In HD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_HoaDon1Layout = new javax.swing.GroupLayout(jPanel_HoaDon1);
        jPanel_HoaDon1.setLayout(jPanel_HoaDon1Layout);
        jPanel_HoaDon1Layout.setHorizontalGroup(
            jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_maVoucher1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_phanTramGiam1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlb_giamgia1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addComponent(txt_tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28))
                    .addComponent(jLabel27)
                    .addComponent(jLabel22)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addComponent(btn_chonVoucher1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_apDung1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_huyKM1))
                    .addComponent(jLabel24)
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tenKH1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txt_sdtKH1)))
                    .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                            .addComponent(btn_chonKH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_huyKH1))
                        .addComponent(jlb_maHoaDon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel21))
                    .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                            .addComponent(txt_tienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel31)
                            .addGap(21, 21, 21)
                            .addComponent(btn_tru1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_HoaDon1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_taoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_thanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_huyHoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel_HoaDon1Layout.createSequentialGroup()
                        .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_tienKhachTra1)
                            .addComponent(txt_thanhtien1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel_HoaDon1Layout.setVerticalGroup(
            jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(15, 15, 15)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb_maHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_huyKH1)
                    .addComponent(btn_chonKH1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sdtKH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(11, 11, 11)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txt_tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_apDung1)
                    .addComponent(btn_huyKM1)
                    .addComponent(btn_chonVoucher1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_maVoucher1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlb_giamgia1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_phanTramGiam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_thanhtien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienKhachTra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(btn_tru1))
                    .addComponent(txt_tienThua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_huyHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_taoHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_HoaDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_BanHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BanHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_HoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrame1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel_BanHang2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_BanHang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel_TableHD2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_TableHD2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setBackground(new java.awt.Color(255, 153, 102));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Hóa Đơn Chờ");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_hoadon2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn ", "Tên Nhân Viên ", "Tên Khách Hàng", "Mã KM", "Ngày Tạo ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hoadon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoadon2MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_hoadon2);

        javax.swing.GroupLayout jPanel_TableHD2Layout = new javax.swing.GroupLayout(jPanel_TableHD2);
        jPanel_TableHD2.setLayout(jPanel_TableHD2Layout);
        jPanel_TableHD2Layout.setHorizontalGroup(
            jPanel_TableHD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jPanel_TableHD2Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_TableHD2Layout.setVerticalGroup(
            jPanel_TableHD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TableHD2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel_GioHang2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_GioHang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setBackground(new java.awt.Color(255, 153, 102));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Giỏ Hàng");
        jLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_giohang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id ctsp", "Tên Sản Phẩm", "Số Lượng ", "Chất Liệu", "Màu Sắc", "Hãng", "Size", "Đơn Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tb_giohang2);

        btn_update2.setText("Chỉnh Sửa");
        btn_update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update2ActionPerformed(evt);
            }
        });

        btn_delete2.setText("Xóa");
        btn_delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete2ActionPerformed(evt);
            }
        });

        btn_clear2.setText("Clear");
        btn_clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_GioHang2Layout = new javax.swing.GroupLayout(jPanel_GioHang2);
        jPanel_GioHang2.setLayout(jPanel_GioHang2Layout);
        jPanel_GioHang2Layout.setHorizontalGroup(
            jPanel_GioHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GioHang2Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane8)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GioHang2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_clear2)
                .addGap(18, 18, 18)
                .addComponent(btn_update2)
                .addGap(47, 47, 47)
                .addComponent(btn_delete2)
                .addGap(110, 110, 110))
        );
        jPanel_GioHang2Layout.setVerticalGroup(
            jPanel_GioHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GioHang2Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel_GioHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update2)
                    .addComponent(btn_delete2)
                    .addComponent(btn_clear2))
                .addContainerGap())
        );

        jPanel_SanPham2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_SanPham2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setBackground(new java.awt.Color(255, 153, 102));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Sản Phẩm ");
        jLabel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_HdSp2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên Sản Phẩm", "Số Lượng ", "Giá Bán", "Danh Mục ", "Chất liệu", "Size", "Hãng", "Đế", "Màu Sắc "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_HdSp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HdSp2MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tb_HdSp2);

        btn_prev2.setText("<<");
        btn_prev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prev2ActionPerformed(evt);
            }
        });

        btn_next2.setText(">>");
        btn_next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_next2ActionPerformed(evt);
            }
        });

        btn_timkiemsp2.setText("Tìm Kiếm");
        btn_timkiemsp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemsp2ActionPerformed(evt);
            }
        });

        btn_them2.setText("Thêm");
        btn_them2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_SanPham2Layout = new javax.swing.GroupLayout(jPanel_SanPham2);
        jPanel_SanPham2.setLayout(jPanel_SanPham2Layout);
        jPanel_SanPham2Layout.setHorizontalGroup(
            jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
            .addGroup(jPanel_SanPham2Layout.createSequentialGroup()
                .addGroup(jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SanPham2Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(btn_prev2)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_page2)
                        .addGap(18, 18, 18)
                        .addComponent(btn_next2))
                    .addGroup(jPanel_SanPham2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(26, 26, 26)
                        .addComponent(btn_timkiemsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_timkiemsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(btn_them2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, Short.MAX_VALUE))
        );
        jPanel_SanPham2Layout.setVerticalGroup(
            jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SanPham2Layout.createSequentialGroup()
                .addGroup(jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(jPanel_SanPham2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_timkiemsp2)
                            .addComponent(txt_timkiemsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_them2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel_SanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_prev2)
                    .addComponent(btn_next2)
                    .addComponent(lbl_page2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_BanHang2Layout = new javax.swing.GroupLayout(jPanel_BanHang2);
        jPanel_BanHang2.setLayout(jPanel_BanHang2Layout);
        jPanel_BanHang2Layout.setHorizontalGroup(
            jPanel_BanHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BanHang2Layout.createSequentialGroup()
                .addGroup(jPanel_BanHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_SanPham2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_TableHD2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_GioHang2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_BanHang2Layout.setVerticalGroup(
            jPanel_BanHang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BanHang2Layout.createSequentialGroup()
                .addComponent(jPanel_TableHD2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_GioHang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_SanPham2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_HoaDon2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_HoaDon2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel37.setText("Form Hóa Đơn");

        jLabel38.setText("Mã Hóa Đơn :");

        jLabel39.setText("Sđt Khách Hàng:");

        jLabel40.setText("Thành Tiền :");

        txt_thanhtien2.setEditable(false);

        jLabel41.setText("Tiền Khách Trả :");

        jLabel42.setText("Tiền Thừa");

        jLabel43.setText("Tổng Tiền :");

        txt_tongTien2.setEditable(false);

        jLabel44.setText("VND");

        jLabel45.setText("VND");

        jLabel46.setText("VND");

        jLabel47.setText("VND");

        btn_taoHoaDon2.setBackground(new java.awt.Color(102, 255, 102));
        btn_taoHoaDon2.setText("Tạo Hóa Đơn");
        btn_taoHoaDon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDon2ActionPerformed(evt);
            }
        });

        btn_thanhToan2.setBackground(new java.awt.Color(102, 255, 102));
        btn_thanhToan2.setText("Thanh Toán");
        btn_thanhToan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToan2ActionPerformed(evt);
            }
        });

        btn_huyHoaDon2.setBackground(new java.awt.Color(255, 102, 102));
        btn_huyHoaDon2.setText("Hủy Hóa Đơn");
        btn_huyHoaDon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyHoaDon2ActionPerformed(evt);
            }
        });

        txt_tienThua2.setEditable(false);

        jLabel48.setText("Tên Khách Hàng : ");

        btn_tru2.setText("Trừ");
        btn_tru2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tru2ActionPerformed(evt);
            }
        });

        jLabel49.setText("Mã :");

        jLabel50.setText("Giảm:");

        btn_apDung2.setBackground(new java.awt.Color(204, 255, 204));
        btn_apDung2.setText("Áp Dụng");
        btn_apDung2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apDung2ActionPerformed(evt);
            }
        });

        btn_huyKM2.setBackground(new java.awt.Color(255, 204, 204));
        btn_huyKM2.setText("Hủy");
        btn_huyKM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyKM2ActionPerformed(evt);
            }
        });

        btn_huyKH2.setBackground(new java.awt.Color(255, 204, 204));
        btn_huyKH2.setText("Hủy");
        btn_huyKH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyKH2ActionPerformed(evt);
            }
        });

        btn_chonKH2.setBackground(new java.awt.Color(204, 255, 204));
        btn_chonKH2.setText("Chọn Khách Hàng");
        btn_chonKH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonKH2ActionPerformed(evt);
            }
        });

        btn_chonVoucher2.setBackground(new java.awt.Color(0, 255, 0));
        btn_chonVoucher2.setText("Voucher");
        btn_chonVoucher2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonVoucher2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 255, 102));
        jButton3.setText("In HD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_HoaDon2Layout = new javax.swing.GroupLayout(jPanel_HoaDon2);
        jPanel_HoaDon2.setLayout(jPanel_HoaDon2Layout);
        jPanel_HoaDon2Layout.setHorizontalGroup(
            jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_maVoucher2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_phanTramGiam2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlb_giamgia2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addComponent(txt_tongTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44))
                    .addComponent(jLabel43)
                    .addComponent(jLabel38)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addComponent(btn_chonVoucher2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_apDung2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_huyKM2))
                    .addComponent(jLabel40)
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tenKH2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txt_sdtKH2)))
                    .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                            .addComponent(btn_chonKH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_huyKH2))
                        .addComponent(jlb_maHoaDon2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel37))
                    .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                            .addComponent(txt_tienThua2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel47)
                            .addGap(21, 21, 21)
                            .addComponent(btn_tru2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_HoaDon2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_taoHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_thanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_huyHoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel_HoaDon2Layout.createSequentialGroup()
                        .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_tienKhachTra2)
                            .addComponent(txt_thanhtien2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel_HoaDon2Layout.setVerticalGroup(
            jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDon2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(15, 15, 15)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb_maHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_huyKH2)
                    .addComponent(btn_chonKH2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sdtKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addGap(11, 11, 11)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txt_tongTien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_apDung2)
                    .addComponent(btn_huyKM2)
                    .addComponent(btn_chonVoucher2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_maVoucher2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlb_giamgia2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_phanTramGiam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_thanhtien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tienKhachTra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(btn_tru2))
                    .addComponent(txt_tienThua2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_huyHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_taoHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_HoaDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_BanHang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_HoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BanHang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_HoaDon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tb_hoadon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadon1MouseClicked

    }//GEN-LAST:event_tb_hoadon1MouseClicked

    private void btn_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update1ActionPerformed

    }//GEN-LAST:event_btn_update1ActionPerformed

    private void btn_delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete1ActionPerformed

    }//GEN-LAST:event_btn_delete1ActionPerformed

    private void btn_clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear1ActionPerformed

    }//GEN-LAST:event_btn_clear1ActionPerformed

    private void tb_HdSp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HdSp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_HdSp1MouseClicked

    private void btn_prev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prev1ActionPerformed

    }//GEN-LAST:event_btn_prev1ActionPerformed

    private void btn_next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_next1ActionPerformed

    }//GEN-LAST:event_btn_next1ActionPerformed

    private void btn_timkiemsp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemsp1ActionPerformed

    }//GEN-LAST:event_btn_timkiemsp1ActionPerformed

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed

    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_taoHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDon1ActionPerformed

    }//GEN-LAST:event_btn_taoHoaDon1ActionPerformed

    private void btn_thanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToan1ActionPerformed

    }//GEN-LAST:event_btn_thanhToan1ActionPerformed

    private void btn_huyHoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyHoaDon1ActionPerformed

    }//GEN-LAST:event_btn_huyHoaDon1ActionPerformed

    private void btn_tru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tru1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tru1ActionPerformed

    private void btn_apDung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apDung1ActionPerformed

    }//GEN-LAST:event_btn_apDung1ActionPerformed

    private void btn_huyKM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyKM1ActionPerformed

    }//GEN-LAST:event_btn_huyKM1ActionPerformed

    private void btn_huyKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyKH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_huyKH1ActionPerformed

    private void btn_chonKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonKH1ActionPerformed

    }//GEN-LAST:event_btn_chonKH1ActionPerformed

    private void btn_chonVoucher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonVoucher1ActionPerformed

    }//GEN-LAST:event_btn_chonVoucher1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tb_hoadon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoadon2MouseClicked
        showDetailHDC();
        fillTableGioHang();
        int rowHD = tb_hoadon2.getSelectedRow();
        if (rowHD >= 0) {
            Object tenKHObject = tb_hoadon2.getValueAt(rowHD, 2);
            String tenKh = (tenKHObject != null) ? tenKHObject.toString() : "";
            txt_tenKH2.setText(tenKh);

            Object maKMObject = tb_hoadon2.getValueAt(rowHD, 3);
            String maKM = (maKMObject != null) ? maKMObject.toString() : "";
            txt_maVoucher2.setText(maKM);

            int row = tb_hoadon2.getSelectedRow();
            String maHD = tb_hoadon2.getValueAt(row, 0).toString();
            String idHD = hdcRepo.getIdHoaDonByMa(maHD);
            String sdt = hdcRepo.getSDTKHbyIDHD(idHD);
            txt_sdtKH2.setText(sdt);
            int row1 = tb_hoadon2.getSelectedRow();
            String maKm1 = tb_hoadon2.getValueAt(row, 3).toString();
            KhuyenMai khuyenMai = hdcRepo.getHinThucGiamibyMa(maKm1);
            if (khuyenMai != null) {
                int hinhThucGiam = khuyenMai.getHinhThucGiam();
                if (hinhThucGiam == 1) {
                    String ptg = hdcRepo.getPhanTramGiambyIdHD(idHD);
                    txt_phanTramGiam2.setText(ptg);
                    jlb_giamgia2.setText("%");
                    try {
                        String tongTienStr = txt_tongTien2.getText().trim().replace(",", "");
                        String phanTramGiamStr = txt_phanTramGiam2.getText().trim();
                        if (!tongTienStr.isEmpty() && !phanTramGiamStr.isEmpty()) {
                            Double tongTien = Double.parseDouble(tongTienStr);
                            Double phanTramGiam = Double.parseDouble(phanTramGiamStr);
                            Double thanhTien = tongTien - ((tongTien * phanTramGiam) / 100);
                            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                            String formattedThanhTien = decimalFormat.format(thanhTien);
                            System.out.println("thanh tien: " + thanhTien);
                            txt_thanhtien2.setText(formattedThanhTien);
                        } else {

                        }
                    } catch (NumberFormatException e) {
                    }
                } else if (hinhThucGiam == 0) {
                    String ptg = hdcRepo.getGiaGiambyIdHD(idHD);
                    txt_phanTramGiam2.setText(ptg);
                    jlb_giamgia2.setText("VND");
                    try {
                        String tongTienStr = txt_tongTien2.getText().trim().replace(",", "");
                        String phanTramGiamStr = txt_phanTramGiam2.getText().trim();
                        if (!tongTienStr.isEmpty() && !phanTramGiamStr.isEmpty()) {
                            Double tongtien = Double.parseDouble(tongTienStr);
                            Double phanTramGiam = Double.parseDouble(phanTramGiamStr);

                            Double thanhTien = tongtien - phanTramGiam;

                            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                            String formattedThanhTien = decimalFormat.format(thanhTien);
                            System.out.println("thanh tien: " + thanhTien);

                            txt_thanhtien2.setText(formattedThanhTien);

                        } else {

                        }
                    } catch (NumberFormatException e) {
                    }
                }
            } else {
                String tongTienStr = txt_tongTien2.getText().trim().replace(",", "");
                Double tongtien = Double.parseDouble(tongTienStr);
                Double thanhTien = tongtien;
                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String formattedThanhTien = decimalFormat.format(thanhTien);
                System.out.println("thanh tien: " + thanhTien);
                txt_thanhtien2.setText(formattedThanhTien);

                txt_phanTramGiam2.setText("");
                jlb_giamgia2.setText("");
            }
            btn_chonKH2.setEnabled(true);
            btn_chonVoucher2.setEnabled(true);
            if (row != -1) {
                System.out.println("row: " + row);
            }
        }
    }//GEN-LAST:event_tb_hoadon2MouseClicked

    private void btn_update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update2ActionPerformed

    }//GEN-LAST:event_btn_update2ActionPerformed

    private void btn_delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete2ActionPerformed
int rowGH = tb_giohang2.getSelectedRow();
        int rowHDC = tb_hoadon2.getSelectedRow();

        String maHD = tb_hoadon2.getValueAt(rowHDC, 0).toString();
        String idHD = hdRepo.getIdHoaDon(maHD);
        String idHDCT = hdcRepo.getIdHDCTbyHD(idHD);

        if (rowGH <= -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa!");
            return;
        }
        int cf = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn xóa sản phẩm '" + tb_giohang2.getValueAt(rowGH, 1) + "' ra khỏi giỏ hàng không?",
                "Xóa giỏ hàng!",
                JOptionPane.YES_NO_OPTION
        );
        if (cf == JOptionPane.NO_OPTION) {
            return;
        }
        if (cf == JOptionPane.YES_OPTION) {
            String idCTSP = tb_giohang2.getValueAt(rowGH, 0).toString();
            int s1_hdct = Integer.parseInt(tb_giohang2.getValueAt(rowGH, 2).toString());

            if (idCTSP == null) {
                JOptionPane.showMessageDialog(this, "Không thể xác định ID của sản phẩm!");
                return;
            }

            Integer s1_ctsp = hdcRepo.getSoLuongByIdCTSP(idCTSP);
            if (s1_ctsp == null) {
                JOptionPane.showMessageDialog(this, "Không thể xác định số lượng của sản phẩm!");
                return;
            }

            int new_s1_ctsp = s1_ctsp + s1_hdct;

            // Cập nhật lại số lượng sản phẩm
            hdcRepo.updateSoLuongChiTietSanPham(new_s1_ctsp, idCTSP);

            // Xóa sản phẩm khỏi giỏ hàng
            hdcRepo.xoaGioHang(idHDCT);

            // Xóa chi tiết hóa đơn
            hdcRepo.xoaHoaDonCT(idCTSP);

            // Cập nhật lại giao diện giỏ hàng
            fillTableGioHang();
            loadtable(listspct);

            JOptionPane.showMessageDialog(this, "Sản phẩm đã được xóa và số lượng đã được cập nhật.");
        }      

    }//GEN-LAST:event_btn_delete2ActionPerformed

    private void btn_clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear2ActionPerformed
        clearFormHoaDon1();
        loadtable(listspct);
    }//GEN-LAST:event_btn_clear2ActionPerformed

    private void tb_HdSp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HdSp2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_HdSp2MouseClicked

    private void btn_prev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prev2ActionPerformed

    }//GEN-LAST:event_btn_prev2ActionPerformed

    private void btn_next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_next2ActionPerformed

    }//GEN-LAST:event_btn_next2ActionPerformed

    private void btn_timkiemsp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemsp2ActionPerformed

    }//GEN-LAST:event_btn_timkiemsp2ActionPerformed

    private void btn_them2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them2ActionPerformed
        int rowHDC = tb_hoadon2.getSelectedRow();
        int rowDSSP = tb_HdSp2.getSelectedRow();
        int sl = 0;

        // Đảm bảo đã chọn hàng trong bảng hóa đơn và bảng sản phẩm
        if (rowHDC <= -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn để thêm sản phẩm");
            return;
        }
        if (rowDSSP <= -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn sản phẩm trong danh sách để thêm");
            return;
        }

        // Nhập số lượng và kiểm tra hợp lệ
        String slNhap = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm");
        try {
            sl = Integer.parseInt(slNhap);
            if (sl <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
            return;
        }

        // Kiểm tra số lượng có sẵn trong kho
        int soLuongTrongKho = Integer.parseInt(tb_HdSp2.getValueAt(rowDSSP, 2).toString());
        if (sl > soLuongTrongKho) {
            JOptionPane.showMessageDialog(this, "Số lượng trong cửa hàng không đủ");
            return;
        }

        // Lấy thông tin sản phẩm từ bảng danh sách sản phẩm
        String idSanPham = tb_HdSp2.getValueAt(rowDSSP, 0).toString();
        String tenSanPham = tb_HdSp2.getValueAt(rowDSSP, 1).toString();
        double donGia = Double.parseDouble(tb_HdSp2.getValueAt(rowDSSP, 3).toString());
        String chatLieu = tb_HdSp2.getValueAt(rowDSSP, 4).toString();
        String mauSac = tb_HdSp2.getValueAt(rowDSSP, 5).toString();
        String hang = tb_HdSp2.getValueAt(rowDSSP, 7).toString();
        String size = tb_HdSp2.getValueAt(rowDSSP, 6).toString();
        double thanhTien = donGia * sl;
        DefaultTableModel gioHangModel = (DefaultTableModel) tb_giohang2.getModel();
        int rowGH = -1;

        // Duyệt qua các hàng trong bảng giỏ hàng để tìm sản phẩm
        for (int i = 0; i < tb_giohang2.getRowCount(); i++) {
            if (tb_giohang2.getValueAt(i, 0).toString().equals(idSanPham)) {
                rowGH = i;
                break;
            }
        }

        if (rowGH != -1) {
            // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
            int soLuongHienTaiTrongGio = Integer.parseInt(tb_giohang2.getValueAt(rowGH, 2).toString());
            int soLuongMoiTrongGio = soLuongHienTaiTrongGio + sl;

            // Kiểm tra số lượng mới trong giỏ hàng không vượt quá số lượng trong kho
            if (soLuongMoiTrongGio > soLuongTrongKho) {
                JOptionPane.showMessageDialog(this, "Số lượng trong cửa hàng không đủ để thêm vào giỏ hàng");
                return;
            }

            // Cập nhật số lượng trong giỏ hàng
            gioHangModel.setValueAt(soLuongMoiTrongGio, rowGH, 2);

        } else {
            // Thêm sản phẩm mới vào giỏ hàng
            Object[] newRow = {idSanPham, tenSanPham, sl, chatLieu, mauSac, hang, size, thanhTien};
            gioHangModel.addRow(newRow);

            // Hiển thị thông báo thông tin sản phẩm vừa thêm vào giỏ hàng
            String message = "Đã thêm sản phẩm vào giỏ hàng:\n"
                    + "ID: " + idSanPham + "\n"
                    + "Tên sản phẩm: " + tenSanPham + "\n"
                    + "Số lượng: " + sl + "\n"
                    + "Đơn giá: " + donGia + " VND\n"
                    + "Thành tiền: " + thanhTien + " VND";
            JOptionPane.showMessageDialog(this, message, "Thông tin sản phẩm đã thêm giỏ Hàng", JOptionPane.INFORMATION_MESSAGE);
        }

        // Cập nhật số lượng sản phẩm trong kho và giỏ hàng
        int soLuongMoiTrongKho = soLuongTrongKho - sl;
        tb_HdSp2.setValueAt(soLuongMoiTrongKho, rowDSSP, 2); // Cập nhật trực tiếp số lượng trong bảng sản phẩm

        // Cập nhật cơ sở dữ liệu
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdHD(hdRepo.getIdHoaDon(tb_hoadon2.getValueAt(rowHDC, 0).toString()));
        hdct.setIdCTG(idSanPham);
        hdct.setDonGia(donGia);
        hdct.setSoLuong(sl);

        hdcRepo.addHoaDonChiTiet(hdct);
        hdcRepo.updateDSSP(soLuongMoiTrongKho, idSanPham);

    }//GEN-LAST:event_btn_them2ActionPerformed

    private void btn_taoHoaDon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDon2ActionPerformed
        ArrayList<HoaDon> list = hdcRepo.getAllHoaDonCho();
        if (list.size() >= 15) {
            JOptionPane.showMessageDialog(this, "Số lượng tối đa 15 hóa đơn !");
            return;
        } else {
            hdcRepo.addHoaDonCho();
        }
        DefaultTableModel model = (DefaultTableModel) tb_giohang2.getModel();
        model.setRowCount(0);

        fillTableHoaDonCho();
        JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công !");
    }//GEN-LAST:event_btn_taoHoaDon2ActionPerformed

    private void btn_thanhToan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToan2ActionPerformed
        thanhtoan();
    }//GEN-LAST:event_btn_thanhToan2ActionPerformed

    private void btn_huyHoaDon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyHoaDon2ActionPerformed
        //Huy HoaDon
        int row = tb_hoadon2.getSelectedRow();
        if (row <= -1) {
            JOptionPane.showMessageDialog(this, "Phải chọn 1 dòng !");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa hóa đơn '" + tb_hoadon2.getValueAt(row, 0) + "' không ???", "Xóa hóa đơn chờ !", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                String maHoaDon = tb_hoadon2.getValueAt(row, 0).toString();
                clearFormGioHang();
                hdcRepo.xoaHoaDonCho(maHoaDon);

                DefaultTableModel model = (DefaultTableModel) tb_giohang2.getModel();
                model.setRowCount(0);
                // trả lại số lượng sản phẩm 
                int rowHD = tb_hoadon2.getSelectedRow();
                String maHD = tb_hoadon2.getValueAt(rowHD, 0).toString();
                String idHD = hdcRepo.getIdHoaDonByMa(maHD);
                ArrayList<HoaDonChiTiet> listCTHD = hdcRepo.getAllHoaDonChiTietTheoIdHD(idHD);
                hdcRepo.xoaHoaDonChiTiet(idHD);

                for (HoaDonChiTiet hoaDonChiTiet : listCTHD) {
                    String chiTietDep = hoaDonChiTiet.getIdCTG();
                    int slDaGiam = hoaDonChiTiet.getSoLuong();
                    hdcRepo.capNhatSoLuongChiTietSanPham(chiTietDep, slDaGiam);
                }

                fillTableHoaDonCho();
                getGioHang();
                clearFormHoaDon1();

                JOptionPane.showMessageDialog(this, "Xóa thành công !");
            }
        }
    }//GEN-LAST:event_btn_huyHoaDon2ActionPerformed

    private void btn_tru2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tru2ActionPerformed
        try {
            String tienKhachTraText = txt_tienKhachTra2.getText().replaceAll(",", "");
            String thanhTienText = txt_thanhtien2.getText().replaceAll(",", "");

            if (!isValidNumberInput(tienKhachTraText) || !isValidNumberInput(thanhTienText)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách trả là số !");
                // return;
            }

            Double tienKhachTra = Double.valueOf(tienKhachTraText);
            Double thanhTien = Double.valueOf(thanhTienText);
            Double tienThua = tienKhachTra - thanhTien;
            // Định dạng số cho việc hiển thị
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            String tienThuaFormatted = decimalFormat.format(tienThua);
            txt_tienThua2.setText(tienThuaFormatted);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ.");
        }

    }//GEN-LAST:event_btn_tru2ActionPerformed

    private void btn_apDung2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apDung2ActionPerformed

    }//GEN-LAST:event_btn_apDung2ActionPerformed

    private void btn_huyKM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyKM2ActionPerformed

    }//GEN-LAST:event_btn_huyKM2ActionPerformed

    private void btn_huyKH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyKH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_huyKH2ActionPerformed

    private void btn_chonKH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonKH2ActionPerformed
        //Chọn khách hàng
    }//GEN-LAST:event_btn_chonKH2ActionPerformed

    private void btn_chonVoucher2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonVoucher2ActionPerformed

        try {

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_chonVoucher2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_apDung1;
    private javax.swing.JButton btn_apDung2;
    private javax.swing.JButton btn_chonKH1;
    private javax.swing.JButton btn_chonKH2;
    private javax.swing.JButton btn_chonVoucher1;
    private javax.swing.JButton btn_chonVoucher2;
    private javax.swing.JButton btn_clear1;
    private javax.swing.JButton btn_clear2;
    private javax.swing.JButton btn_delete1;
    private javax.swing.JButton btn_delete2;
    private javax.swing.JButton btn_huyHoaDon1;
    private javax.swing.JButton btn_huyHoaDon2;
    private javax.swing.JButton btn_huyKH1;
    private javax.swing.JButton btn_huyKH2;
    private javax.swing.JButton btn_huyKM1;
    private javax.swing.JButton btn_huyKM2;
    private javax.swing.JButton btn_next1;
    private javax.swing.JButton btn_next2;
    private javax.swing.JButton btn_prev1;
    private javax.swing.JButton btn_prev2;
    private javax.swing.JButton btn_taoHoaDon1;
    private javax.swing.JButton btn_taoHoaDon2;
    private javax.swing.JButton btn_thanhToan1;
    private javax.swing.JButton btn_thanhToan2;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_them2;
    private javax.swing.JButton btn_timkiemsp1;
    private javax.swing.JButton btn_timkiemsp2;
    private javax.swing.JButton btn_tru1;
    private javax.swing.JButton btn_tru2;
    private javax.swing.JButton btn_update1;
    private javax.swing.JButton btn_update2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_BanHang1;
    private javax.swing.JPanel jPanel_BanHang2;
    private javax.swing.JPanel jPanel_GioHang1;
    private javax.swing.JPanel jPanel_GioHang2;
    private javax.swing.JPanel jPanel_HoaDon1;
    private javax.swing.JPanel jPanel_HoaDon2;
    private javax.swing.JPanel jPanel_SanPham1;
    private javax.swing.JPanel jPanel_SanPham2;
    private javax.swing.JPanel jPanel_TableHD1;
    private javax.swing.JPanel jPanel_TableHD2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel jlb_giamgia1;
    private javax.swing.JLabel jlb_giamgia2;
    private javax.swing.JLabel jlb_maHoaDon1;
    private javax.swing.JLabel jlb_maHoaDon2;
    private javax.swing.JLabel lbl_page1;
    private javax.swing.JLabel lbl_page2;
    private javax.swing.JTable tb_HdSp1;
    private javax.swing.JTable tb_HdSp2;
    private javax.swing.JTable tb_giohang1;
    private javax.swing.JTable tb_giohang2;
    private javax.swing.JTable tb_hoadon1;
    private javax.swing.JTable tb_hoadon2;
    private javax.swing.JTextField txt_maVoucher1;
    private javax.swing.JTextField txt_maVoucher2;
    private javax.swing.JTextField txt_phanTramGiam1;
    private javax.swing.JTextField txt_phanTramGiam2;
    private javax.swing.JTextField txt_sdtKH1;
    private javax.swing.JTextField txt_sdtKH2;
    private javax.swing.JTextField txt_tenKH1;
    private javax.swing.JTextField txt_tenKH2;
    private javax.swing.JTextField txt_thanhtien1;
    private javax.swing.JTextField txt_thanhtien2;
    private javax.swing.JTextField txt_tienKhachTra1;
    private javax.swing.JTextField txt_tienKhachTra2;
    private javax.swing.JTextField txt_tienThua1;
    private javax.swing.JTextField txt_tienThua2;
    private javax.swing.JTextField txt_timkiemsp1;
    private javax.swing.JTextField txt_timkiemsp2;
    private javax.swing.JTextField txt_tongTien1;
    private javax.swing.JTextField txt_tongTien2;
    // End of variables declaration//GEN-END:variables
}
