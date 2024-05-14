/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ChiTietGiay;
import Service.SanPhamChiTietService;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Demo extends javax.swing.JFrame {

   SanPhamChiTietService ctgservice = new SanPhamChiTietService();
    ArrayList<ChiTietGiay> listspct = ctgservice.getChiTietSanPham();
    public Demo() {
        initComponents();
        loadtableSanPham(listspct);
    }
      public void loadtableSanPham(ArrayList<ChiTietGiay> ctg) {
        DefaultTableModel model = (DefaultTableModel) tb_HdSp1.getModel();
        model.setRowCount(0);
        for (ChiTietGiay chiTietGiay : ctg) {
            Object[] data = new Object[]{
                chiTietGiay.getId(),
                chiTietGiay.getIdSanPham().getTen(),
                chiTietGiay.getSoLuong(),
                chiTietGiay.getGiaBan(),
                chiTietGiay.getIdDanhMuc().getTen(),
                chiTietGiay.getIdChatLieu().getTen(),
                chiTietGiay.getIdSize().getKichCo(),
                chiTietGiay.getIdNSX().getTen(),
                chiTietGiay.getIdDe().getTen(),
                chiTietGiay.getIdMauSac().getMauSac()
        };
              model.addRow(data);
    }
        listspct = ctg;

}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_BanHang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_BanHang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_HoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Demo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_apDung1;
    private javax.swing.JButton btn_chonKH1;
    private javax.swing.JButton btn_chonVoucher1;
    private javax.swing.JButton btn_clear1;
    private javax.swing.JButton btn_delete1;
    private javax.swing.JButton btn_huyHoaDon1;
    private javax.swing.JButton btn_huyKH1;
    private javax.swing.JButton btn_huyKM1;
    private javax.swing.JButton btn_next1;
    private javax.swing.JButton btn_prev1;
    private javax.swing.JButton btn_taoHoaDon1;
    private javax.swing.JButton btn_thanhToan1;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_timkiemsp1;
    private javax.swing.JButton btn_tru1;
    private javax.swing.JButton btn_update1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel_BanHang1;
    private javax.swing.JPanel jPanel_GioHang1;
    private javax.swing.JPanel jPanel_HoaDon1;
    private javax.swing.JPanel jPanel_SanPham1;
    private javax.swing.JPanel jPanel_TableHD1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel jlb_giamgia1;
    private javax.swing.JLabel jlb_maHoaDon1;
    private javax.swing.JLabel lbl_page1;
    private javax.swing.JTable tb_HdSp1;
    private javax.swing.JTable tb_giohang1;
    private javax.swing.JTable tb_hoadon1;
    private javax.swing.JTextField txt_maVoucher1;
    private javax.swing.JTextField txt_phanTramGiam1;
    private javax.swing.JTextField txt_sdtKH1;
    private javax.swing.JTextField txt_tenKH1;
    private javax.swing.JTextField txt_thanhtien1;
    private javax.swing.JTextField txt_tienKhachTra1;
    private javax.swing.JTextField txt_tienThua1;
    private javax.swing.JTextField txt_timkiemsp1;
    private javax.swing.JTextField txt_tongTien1;
    // End of variables declaration//GEN-END:variables
}
