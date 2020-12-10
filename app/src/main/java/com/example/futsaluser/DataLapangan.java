package com.example.futsaluser;

public class DataLapangan extends DataBooking {
    String id;
    String lapangan;
    String status;
    //String nama;
    //String tanggal;
    //String jam;
    //String telephone;

    public DataLapangan() {
    }

    public DataLapangan(String id,String lapangan, String status){//, String nama, String tanggal,String jam, String telephone) {
        this.id = id;
        this.lapangan = lapangan;
        this.status = status;
        //this.nama = nama;
        //this.tanggal = tanggal;
        //this.jam = jam;
        //this.telephone = telephone;
    }

    /*public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
