public class App {
    public static void main(String[] args) {
        Kantor kantor = new Kantor();

        kantor.tambahPegawai(new Direktur("Budi", 50, 10000000, 5));
        kantor.tambahPegawai(new Manager("Andi", 40, 7000000, 4));
        kantor.tambahPegawai(new Supervisor("Siti", 35, 5000000, 3));
        kantor.tambahPegawai(new Operasional("Joko", 30, 3000000, 2));

        kantor.tampilkanNamaPegawai();

        System.out.println("Rata-rata Gaji: Rp " + kantor.hitungRataRataGaji());

        kantor.hapusPegawai("Andi");
        System.out.println("\nSetelah Menghapus Andi:");
        kantor.tampilkanNamaPegawai();
    }
}
