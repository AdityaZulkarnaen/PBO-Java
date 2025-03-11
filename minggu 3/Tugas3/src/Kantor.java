import java.util.ArrayList;
import java.util.List;

public class Kantor {
    private List<Pegawai> daftarPegawai = new ArrayList<>();

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
            daftarPegawai.forEach(p -> System.out.println("- " + p.getNama()));
        }
    }
}
