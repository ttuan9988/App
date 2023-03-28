using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Entity
{
    public class TK
    {
        [Key]
        public string taikhoan { get; set; }
        public string matkhau { get; set; }
        public string quyen { get; set; }
        public string ten { get; set; }
        public string sdt { get; set; }
        public string tien { get; set; }
        public string vitri { get; set; }
    }
}
