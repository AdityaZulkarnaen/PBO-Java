import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScoreManager {
    private Map<String, Integer> scores;
    private Scanner scanner;
    
    public ScoreManager() {
        scores = new HashMap<>();
        scanner = new Scanner(System.in);
    }
    
    public void tambahNilaiDariInput() {
        System.out.print("Masukkan nama anak: ");
        String nama = scanner.nextLine();
        
        int nilai = 0;
        boolean inputValid = false;
        
        while (!inputValid) {
            try {
                System.out.print("Masukkan nilai " + nama + ": ");
                nilai = Integer.parseInt(scanner.nextLine());
                inputValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Nilai harus berupa angka.");
            }
        }
        
        scores.put(nama, nilai);
        System.out.println("Nilai " + nama + " berhasil ditambahkan: " + nilai);
    }
    
    public void tambahNilai(String nama, int nilai) {
        scores.put(nama, nilai);
        System.out.println("Nilai " + nama + " berhasil ditambahkan: " + nilai);
    }
    
    public int getNilai(String nama) {
        if (scores.containsKey(nama)) {
            return scores.get(nama);
        } else {
            System.out.println("Nama tidak ditemukan!");
            return -1;
        }
    }
    
    public int hitungTotalNilai() {
        int total = 0;
        for (int nilai : scores.values()) {
            total += nilai;
        }
        return total;
    }
    
    public double hitungRataRata() {
        if (scores.isEmpty()) {
            return 0;
        }
        return (double) hitungTotalNilai() / scores.size();
    }
    
    public int nilaiTertinggi() {
        if (scores.isEmpty()) {
            return 0;
        }
        return Collections.max(scores.values());
    }
    
    public String anakDenganNilaiTertinggi() {
        if (scores.isEmpty()) {
            return "Tidak ada data";
        }
        
        int maxNilai = nilaiTertinggi();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() == maxNilai) {
                return entry.getKey();
            }
        }
        return "Tidak ditemukan";
    }
    
    public int nilaiTerendah() {
        if (scores.isEmpty()) {
            return 0;
        }
        return Collections.min(scores.values());
    }
    
    public String anakDenganNilaiTerendah() {
        if (scores.isEmpty()) {
            return "Tidak ada data";
        }
        
        int minNilai = nilaiTerendah();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() == minNilai) {
                return entry.getKey();
            }
        }
        return "Tidak ditemukan";
    }
    
    public int selisihNilai() {
        if (scores.isEmpty()) {
            return 0;
        }
        return nilaiTertinggi() - nilaiTerendah();
    }
    
    public void tampilkanSemuaNilai() {
        if (scores.isEmpty()) {
            System.out.println("Belum ada data nilai yang tersimpan.");
            return;
        }
        
        System.out.println("=== Data Nilai Anak ===");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public void tampilkanStatistik() {
        if (scores.isEmpty()) {
            System.out.println("Belum ada data nilai yang tersimpan.");
            return;
        }
        
        System.out.println("=== Statistik Nilai ===");
        System.out.println("Jumlah anak: " + scores.size());
        System.out.println("Total nilai: " + hitungTotalNilai());
        System.out.println("Rata-rata nilai: " + String.format("%.2f", hitungRataRata()));
        System.out.println("Nilai tertinggi: " + nilaiTertinggi() + " (Dimiliki oleh: " + anakDenganNilaiTertinggi() + ")");
        System.out.println("Nilai terendah: " + nilaiTerendah() + " (Dimiliki oleh: " + anakDenganNilaiTerendah() + ")");
        System.out.println("Selisih nilai tertinggi dan terendah: " + selisihNilai());
    }
    
    public void jalankanProgram() {
        boolean lanjut = true;
        
        while (lanjut) {
            System.out.println("\n=== Menu ScoreManager ===");
            System.out.println("1. Tambah Nilai Anak");
            System.out.println("2. Lihat Semua Nilai");
            System.out.println("3. Lihat Statistik Nilai");
            System.out.println("4. Cari Nilai Anak");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            
            int pilihan = -1;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                continue;
            }
            
            switch (pilihan) {
                case 1:
                    tambahNilaiDariInput();
                    break;
                case 2:
                    tampilkanSemuaNilai();
                    break;
                case 3:
                    tampilkanStatistik();
                    break;
                case 4:
                    System.out.print("Masukkan nama anak: ");
                    String nama = scanner.nextLine();
                    int nilai = getNilai(nama);
                    if (nilai != -1) {
                        System.out.println("Nilai " + nama + ": " + nilai);
                    }
                    break;
                case 0:
                    lanjut = false;
                    System.out.println("Terima kasih telah menggunakan ScoreManager!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        System.out.println("Selamat datang di ScoreManager!");
        ScoreManager manager = new ScoreManager();
        manager.jalankanProgram();
    }
}