import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class Kantor {
    private List<Pegawai> daftarPegawai = new ArrayList<>();
    private static final NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("id", "ID"));
    
    public Kantor() {
        currencyFormatter.setGroupingUsed(true);
        currencyFormatter.setMaximumFractionDigits(0);
    }

    public void tambahPegawai(Pegawai pegawai) {
        daftarPegawai.add(pegawai);
    }

    public void hapusPegawai(String nama) {
        daftarPegawai.removeIf(p -> p.getNama().equalsIgnoreCase(nama));
    }

    public double hitungRataRataGaji() {
        if (daftarPegawai.isEmpty()) return 0;
        double totalGaji = daftarPegawai.stream().mapToDouble(Pegawai::getGaji).sum();
        return totalGaji / daftarPegawai.size();
    }

    public void tampilkanNamaPegawai() {
        if (daftarPegawai.isEmpty()) {
            System.out.println("Tidak ada pegawai.");
        } else {
            System.out.println("Daftar Pegawai:");
            for (Pegawai p : daftarPegawai) {
                System.out.println("- " + p.getNama() + " (Gaji: Rp " + formatCurrency(p.getGaji()) + ")");
            }
        }
    }
    
    private String formatCurrency(double amount) {
        return currencyFormatter.format(amount);
    }
}