﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Entity
{

    public class PHIEUTHUEXE 
    {
        [Key]
        public int maphieu { get; set; }
        public string ngaythue { get; set; }
        public string ngaytra { get; set; }
        public string tiencoc { get; set; }
        public string taikhoan { get; set; }
        public string biensoxe { get; set; }
        public string duyet { get; set; }
        public string thoigian { get; set; }
        public string tienxe { get; set; }
        public string xem { get; set; }
        public string mahd { get; set; }
        public string banglai { get; set; }

    }
}
