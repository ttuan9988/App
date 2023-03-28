using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Entity
{
    public class XETHUE
    {
        [Key]
        public int maxe { get; set; }
        public string tenxe { get; set; }
        public string loaixe { get; set; }
        public string biensoxe { get; set; }
        public string giaxe { get; set; }
        public string hinhanh { get; set; }
        public string tinhtrang { get; set; }
        public string maphieu { get; set; }
        public string xe { get; set; }
        public string trangthai { get; set; }
        public string kiemdinh { get; set; }
        public string hanthaynhot { get; set; }
        public string vitrixe { get; set; }
    }
}
