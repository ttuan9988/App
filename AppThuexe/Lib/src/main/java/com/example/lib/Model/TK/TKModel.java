package com.example.lib.Model.TK;

import java.io.Serializable;
import java.util.List;

public class TKModel implements Serializable
{
    private List<Data> data;

    private String status;

    private String message1;

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getMessage1 ()
    {
        return message1;
    }

    public void setMessage1 (String message1)
    {
        this.message1 = message1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+", message1 = "+message1+"]";
    }
    public class Data
    {
        private String quyen;

        private String sdt;

        private String matkhau;

        private String taikhoan;

        private String tien;

        private String vitri;

        private String ten;

        public String getQuyen ()
        {
            return quyen;
        }

        public void setQuyen (String quyen)
        {
            this.quyen = quyen;
        }

        public String getSdt ()
        {
            return sdt;
        }

        public void setSdt (String sdt)
        {
            this.sdt = sdt;
        }

        public String getMatkhau ()
        {
            return matkhau;
        }

        public void setMatkhau (String matkhau)
        {
            this.matkhau = matkhau;
        }

        public String getTaikhoan ()
        {
            return taikhoan;
        }

        public void setTaikhoan (String taikhoan)
        {
            this.taikhoan = taikhoan;
        }

        public String getTien ()
        {
            return tien;
        }

        public void setTien (String tien)
        {
            this.tien = tien;
        }

        public String getVitri ()
        {
            return vitri;
        }

        public void setVitri (String vitri)
        {
            this.vitri = vitri;
        }

        public String getTen ()
        {
            return ten;
        }

        public void setTen (String ten)
        {
            this.ten = ten;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [quyen = "+quyen+", sdt = "+sdt+", matkhau = "+matkhau+", taikhoan = "+taikhoan+", tien = "+tien+", vitri = "+vitri+", ten = "+ten+"]";
        }
    }

}
