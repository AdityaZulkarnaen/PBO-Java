import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class App {
    private static final NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("id", "ID"));
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kantor kantor = new Kantor();
        int pilihan = 0;

        currencyFormatter.setGroupingUsed(true);
        currencyFormatter.setMaximumFractionDigits(0);
        
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan: ");
            
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                
                switch (pilihan) {
                    case 1: 
                        tambahPegawai(scanner, kantor);
                        break;
                    case 2: 
                        kantor.tampilkanNamaPegawai();
                        break;
                    case 3: 
                        System.out.println("Rata-rata Gaji: Rp " + formatCurrency(kantor.hitungRataRataGaji()));
                        break;
                    case 4: 
                        hapusPegawai(scanner, kantor);
                        break;
                    case 5: 
                        System.out.println("Terima kasih telah menggunakan sistem.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
            
            System.out.println(); 
        } while (pilihan != 5);
        
        scanner.close();
    }
    
    private static String formatCurrency(double amount) {
        return currencyFormatter.format(amount);
    }
    
    private static void tampilkanMenu() {
        System.out.println("=== SISTEM MANAJEMEN PEGAWAI ===");
        System.out.println("1. Tambah Pegawai");
        System.out.println("2. Tampilkan Daftar Pegawai");
        System.out.println("3. Hitung Rata-rata Gaji");
        System.out.println("4. Hapus Pegawai");
        System.out.println("5. Keluar");
    }
    
    private static void tambahPegawai(Scanner scanner, Kantor kantor) {
        System.out.println("\n=== TAMBAH PEGAWAI ===");
        System.out.println("Pilih jenis pegawai:");
        System.out.println("1. Direktur");
        System.out.println("2. Manager");
        System.out.println("3. Supervisor");
        System.out.println("4. Operasional");
        System.out.print("Masukkan pilihan: ");
        
        try {
            int jenisPegawai = Integer.parseInt(scanner.nextLine());
            
            if (jenisPegawai < 1 || jenisPegawai > 4) {
                System.out.println("Jenis pegawai tidak valid!");
                return;
            }
            
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            
            System.out.print("Umur: ");
            int umur = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Gaji Dasar: ");
            double gajiDasar = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Lama Kerja (tahun): ");
            int lamaKerja = Integer.parseInt(scanner.nextLine());
            
            Pegawai pegawai = null;
            
            switch (jenisPegawai) {
                case 1:
                    pegawai = new Direktur(nama, umur, gajiDasar, lamaKerja);
                    break;
                case 2:
                    pegawai = new Manager(nama, umur, gajiDasar, lamaKerja);
                    break;
                case 3:
                    pegawai = new Supervisor(nama, umur, gajiDasar, lamaKerja);
                    break;
                case 4:
                    pegawai = new Operasional(nama, umur, gajiDasar, lamaKerja);
                    break;
            }
            
            kantor.tambahPegawai(pegawai);
            System.out.println("Pegawai " + nama + " berhasil ditambahkan!");
            System.out.println("Gaji: Rp " + formatCurrency(pegawai.getGaji()));
            
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
        }
    }
    
    private static void hapusPegawai(Scanner scanner, Kantor kantor) {
        System.out.print("Masukkan nama pegawai yang akan dihapus: ");
        String nama = scanner.nextLine();
        
        kantor.hapusPegawai(nama);
        System.out.println("Pegawai dengan nama " + nama + " telah dihapus (jika ada).");
    }
}