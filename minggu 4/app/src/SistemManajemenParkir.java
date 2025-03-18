import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

class Kendaraan {
    private String nomorPlat;
    private int jumlahRoda;
    private LocalDateTime waktuMasuk;
    
    public Kendaraan(String nomorPlat, int jumlahRoda) {
        this.nomorPlat = nomorPlat;
        this.jumlahRoda = jumlahRoda;
        this.waktuMasuk = LocalDateTime.now();
    }
    
    public String getNomorPlat() {
        return nomorPlat;
    }
    
    public int getJumlahRoda() {
        return jumlahRoda;
    }
    
    public LocalDateTime getWaktuMasuk() {
        return waktuMasuk;
    }
    
    public String getJenisKendaraan() {
        return (jumlahRoda == 2) ? "Motor" : "Mobil";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return getJenisKendaraan() + " - Plat: " + nomorPlat + 
               " | Roda: " + jumlahRoda + 
               " | Masuk: " + waktuMasuk.format(formatter);
    }
}

class Lantai {
    private int nomorLantai;
    private List<Kendaraan> daftarKendaraan;
    private final int KAPASITAS_RODA = 12;
    
    public Lantai(int nomorLantai) {
        this.nomorLantai = nomorLantai;
        this.daftarKendaraan = new ArrayList<>();
    }
    
    public int getNomorLantai() {
        return nomorLantai;
    }
    
    public List<Kendaraan> getDaftarKendaraan() {
        return daftarKendaraan;
    }
    
    public int getJumlahRodaSaatIni() {
        int totalRoda = 0;
        for (Kendaraan kendaraan : daftarKendaraan) {
            totalRoda += kendaraan.getJumlahRoda();
        }
        return totalRoda;
    }
    
    public int getSisaKapasitasRoda() {
        return KAPASITAS_RODA - getJumlahRodaSaatIni();
    }
    
    public boolean tambahKendaraan(Kendaraan kendaraan) {
        if (kendaraan.getJumlahRoda() <= getSisaKapasitasRoda()) {
            daftarKendaraan.add(kendaraan);
            return true;
        }
        return false;
    }
    
    public Kendaraan keluarkanKendaraan(String nomorPlat) {
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan kendaraan = daftarKendaraan.get(i);
            if (kendaraan.getNomorPlat().equalsIgnoreCase(nomorPlat)) {
                daftarKendaraan.remove(i);
                return kendaraan;
            }
        }
        return null;
    }
    
    public Kendaraan cariKendaraan(String nomorPlat) {
        for (Kendaraan kendaraan : daftarKendaraan) {
            if (kendaraan.getNomorPlat().equalsIgnoreCase(nomorPlat)) {
                return kendaraan;
            }
        }
        return null;
    }
}

class SistemParkir {
    private List<Lantai> daftarLantai;
    private final int BIAYA_PARKIR_PER_JAM_MOTOR = 2000;
    private final int BIAYA_PARKIR_PER_JAM_MOBIL = 5000;
    
    public SistemParkir(int jumlahLantai) {
        this.daftarLantai = new ArrayList<>();
        for (int i = 1; i <= jumlahLantai; i++) {
            daftarLantai.add(new Lantai(i));
        }
    }
    
    public void tampilkanDaftarKendaraan() {
        System.out.println("\n===== DAFTAR KENDARAAN PARKIR =====");
        for (Lantai lantai : daftarLantai) {
            System.out.println("Lantai " + lantai.getNomorLantai() + ":");
            System.out.println("Jumlah roda saat ini: " + lantai.getJumlahRodaSaatIni() + 
                              " | Sisa kapasitas: " + lantai.getSisaKapasitasRoda() + " roda");
            
            List<Kendaraan> kendaraanList = lantai.getDaftarKendaraan();
            if (kendaraanList.isEmpty()) {
                System.out.println("- Tidak ada kendaraan");
            } else {
                for (int i = 0; i < kendaraanList.size(); i++) {
                    System.out.println((i+1) + ". " + kendaraanList.get(i));
                }
            }
            System.out.println();
        }
    }
    
    public boolean tambahKendaraan(Scanner scanner) {
        System.out.println("\n===== TAMBAH KENDARAAN =====");
        System.out.print("Masukkan nomor plat: ");
        String nomorPlat = scanner.nextLine();
        
        System.out.println("Jenis kendaraan:");
        System.out.println("1. Motor (2 roda)");
        System.out.println("2. Mobil (4 roda)");
        System.out.print("Pilih jenis kendaraan (1/2): ");
        
        int jenisKendaraan;
        try {
            jenisKendaraan = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return false;
        }
        
        int jumlahRoda;
        if (jenisKendaraan == 1) {
            jumlahRoda = 2;
        } else if (jenisKendaraan == 2) {
            jumlahRoda = 4;
        } else {
            System.out.println("Pilihan tidak valid!");
            return false;
        }
        
        System.out.print("Masukkan nomor lantai (1-" + daftarLantai.size() + "): ");
        int nomorLantai;
        try {
            nomorLantai = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return false;
        }
        
        if (nomorLantai < 1 || nomorLantai > daftarLantai.size()) {
            System.out.println("Nomor lantai tidak valid!");
            return false;
        }
        
        Lantai lantai = daftarLantai.get(nomorLantai - 1);
        
        for (Lantai l : daftarLantai) {
            if (l.cariKendaraan(nomorPlat) != null) {
                System.out.println("Kendaraan dengan nomor plat " + nomorPlat + " sudah terparkir!");
                return false;
            }
        }
        
        Kendaraan kendaraan = new Kendaraan(nomorPlat, jumlahRoda);
        
        if (lantai.tambahKendaraan(kendaraan)) {
            System.out.println(kendaraan.getJenisKendaraan() + " dengan nomor plat " + nomorPlat + 
                              " berhasil diparkir di lantai " + nomorLantai);
            return true;
        } else {
            System.out.println("Lantai " + nomorLantai + " tidak cukup ruang untuk kendaraan ini!");
            return false;
        }
    }
    
    public boolean pindahkanKendaraan(Scanner scanner) {
        System.out.println("\n===== PINDAHKAN KENDARAAN =====");
        System.out.print("Masukkan nomor plat kendaraan yang akan dipindahkan: ");
        String nomorPlat = scanner.nextLine();
        
        Lantai lantaiAsal = null;
        Kendaraan kendaraan = null;
        
        for (Lantai lantai : daftarLantai) {
            Kendaraan k = lantai.cariKendaraan(nomorPlat);
            if (k != null) {
                lantaiAsal = lantai;
                kendaraan = k;
                break;
            }
        }
        
        if (lantaiAsal == null || kendaraan == null) {
            System.out.println("Kendaraan dengan nomor plat " + nomorPlat + " tidak ditemukan!");
            return false;
        }
        
        System.out.println("Kendaraan ditemukan di lantai " + lantaiAsal.getNomorLantai());
        System.out.print("Masukkan nomor lantai tujuan (1-" + daftarLantai.size() + "): ");
        
        int nomorLantaiTujuan;
        try {
            nomorLantaiTujuan = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return false;
        }
        
        if (nomorLantaiTujuan < 1 || nomorLantaiTujuan > daftarLantai.size()) {
            System.out.println("Nomor lantai tidak valid!");
            return false;
        }
        
        if (nomorLantaiTujuan == lantaiAsal.getNomorLantai()) {
            System.out.println("Kendaraan sudah berada di lantai " + nomorLantaiTujuan + "!");
            return false;
        }
        
        Lantai lantaiTujuan = daftarLantai.get(nomorLantaiTujuan - 1);
        
        if (lantaiTujuan.getSisaKapasitasRoda() >= kendaraan.getJumlahRoda()) {
            kendaraan = lantaiAsal.keluarkanKendaraan(nomorPlat);
            lantaiTujuan.tambahKendaraan(kendaraan);
            System.out.println(kendaraan.getJenisKendaraan() + " dengan nomor plat " + nomorPlat + 
                              " berhasil dipindahkan dari lantai " + lantaiAsal.getNomorLantai() + 
                              " ke lantai " + lantaiTujuan.getNomorLantai());
            return true;
        } else {
            System.out.println("Lantai " + nomorLantaiTujuan + " tidak cukup ruang untuk kendaraan ini!");
            return false;
        }
    }
    
    public boolean keluarkanKendaraan(Scanner scanner) {
        System.out.println("\n===== KELUARKAN KENDARAAN =====");
        System.out.print("Masukkan nomor plat kendaraan yang akan dikeluarkan: ");
        String nomorPlat = scanner.nextLine();
        
        Lantai lantaiParkir = null;
        Kendaraan kendaraan = null;
        
        for (Lantai lantai : daftarLantai) {
            Kendaraan k = lantai.cariKendaraan(nomorPlat);
            if (k != null) {
                lantaiParkir = lantai;
                kendaraan = k;
                break;
            }
        }
        
        if (lantaiParkir == null || kendaraan == null) {
            System.out.println("Kendaraan dengan nomor plat " + nomorPlat + " tidak ditemukan!");
            return false;
        }
        
        kendaraan = lantaiParkir.keluarkanKendaraan(nomorPlat);
        
        LocalDateTime waktuKeluar = LocalDateTime.now();
        long durasiJam = ChronoUnit.HOURS.between(kendaraan.getWaktuMasuk(), waktuKeluar);
        if (durasiJam < 1) durasiJam = 1; 
        
        int biaya;
        if (kendaraan.getJumlahRoda() == 2) { 
            biaya = (int) (durasiJam * BIAYA_PARKIR_PER_JAM_MOTOR);
        } else { 
            biaya = (int) (durasiJam * BIAYA_PARKIR_PER_JAM_MOBIL);
        }
        
        System.out.println(kendaraan.getJenisKendaraan() + " dengan nomor plat " + nomorPlat + 
                          " berhasil dikeluarkan dari lantai " + lantaiParkir.getNomorLantai());
        System.out.println("Durasi parkir: " + durasiJam + " jam");
        System.out.println("Biaya parkir: Rp " + formatCurrency(biaya));
        return true;
    }
    
    private String formatCurrency(int amount) {
        StringBuilder formatted = new StringBuilder();
        String amountStr = String.valueOf(amount);
        int length = amountStr.length();
        
        for (int i = 0; i < length; i++) {
            formatted.append(amountStr.charAt(i));
            if ((length - i - 1) % 3 == 0 && i < length - 1) {
                formatted.append('.');
            }
        }
        
        return formatted.toString();
    }
}

public class SistemManajemenParkir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemParkir sistemParkir = new SistemParkir(5);
        int pilihan = 0;
        
        System.out.println("=== SISTEM MANAJEMEN PARKIR ===");
        System.out.println("Kapasitas: 5 lantai, masing-masing maksimal 12 roda");
        
        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tampilkan list kendaraan di setiap lantai");
            System.out.println("2. Tambah kendaraan");
            System.out.println("3. Pindahkan kendaraan");
            System.out.println("4. Keluarkan kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                
                switch (pilihan) {
                    case 1:
                        sistemParkir.tampilkanDaftarKendaraan();
                        break;
                    case 2:
                        sistemParkir.tambahKendaraan(scanner);
                        break;
                    case 3:
                        sistemParkir.pindahkanKendaraan(scanner);
                        break;
                    case 4:
                        sistemParkir.keluarkanKendaraan(scanner);
                        break;
                    case 5:
                        System.out.println("Terima kasih telah menggunakan Sistem Manajemen Parkir!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid!");
            }
            
        } while (pilihan != 5);
        
        scanner.close();
    }
}