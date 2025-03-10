import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UangIndonesia {
    public enum MataUang {
        SERIBU(1000),
        DUARIBU(2000),
        LIMARIBU(5000),
        SEPULUHRIBU(10000),
        DUAPULUHRIBU(20000),
        LIMAPULUHRIBU(50000),
        SERATUSRIBU(100000);
        
        private final int nilai;
        
        MataUang(int nilai) {
            this.nilai = nilai;
        }
        
        public int getNilai() {
            return nilai;
        }
        
        @Override
        public String toString() {
            return "Rp " + nilai;
        }
    }
    
    public static class KasUang {
        private Map<MataUang, Integer> uangMap;
        private Scanner scanner;
        
        public KasUang() {
            uangMap = new HashMap<>();
            scanner = new Scanner(System.in);
            
            for (MataUang uang : MataUang.values()) {
                uangMap.put(uang, 0);
            }
        }
        
        public void tambahUang(MataUang uang, int jumlah) {
            if (jumlah < 0) {
                System.out.println("Jumlah tidak valid!");
                return;
            }
            
            int jumlahSekarang = uangMap.get(uang);
            uangMap.put(uang, jumlahSekarang + jumlah);
            System.out.println("Berhasil menambahkan " + jumlah + " lembar " + uang);
        }
        
        public void inputJumlahUang() {
            for (MataUang uang : MataUang.values()) {
                boolean inputValid = false;
                
                while (!inputValid) {
                    try {
                        System.out.print("Masukkan jumlah lembar " + uang + ": ");
                        int jumlah = Integer.parseInt(scanner.nextLine());
                        
                        if (jumlah < 0) {
                            System.out.println("Jumlah tidak boleh negatif!");
                            continue;
                        }
                        
                        tambahUang(uang, jumlah);
                        inputValid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid! Harap masukkan angka.");
                    }
                }
            }
        }
        
        public void tampilkanJumlahUang() {
            System.out.println("\n=== Jumlah Uang Per Nominal ===");
            
            for (MataUang uang : MataUang.values()) {
                int jumlah = uangMap.get(uang);
                System.out.println(uang + ": " + jumlah + " lembar");
            }
        }
        
        public long hitungTotalNilai() {
            long total = 0;
            
            for (MataUang uang : MataUang.values()) {
                int jumlah = uangMap.get(uang);
                total += (long) uang.getNilai() * jumlah;
            }
            
            return total;
        }
        
        public void tampilkanTotalNilai() {
            long total = hitungTotalNilai();
            System.out.println("\n=== Total Nilai Uang ===");
            System.out.println("Total: Rp " + total);
        }

        public void tampilkanDetailNilai() {
            System.out.println("\n=== Detail Nilai Per Nominal ===");
            
            for (MataUang uang : MataUang.values()) {
                int jumlah = uangMap.get(uang);
                long nilaiTotal = (long) uang.getNilai() * jumlah;
                System.out.println(uang + " x " + jumlah + " lembar = Rp " + nilaiTotal);
            }
        }
        
        public void tutup() {
            scanner.close();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Program Perhitungan Uang Indonesia ===");
        
        KasUang kas = new KasUang();
        kas.inputJumlahUang();
        kas.tampilkanJumlahUang();
        kas.tampilkanDetailNilai();
        kas.tampilkanTotalNilai();
        kas.tutup();
    }
}