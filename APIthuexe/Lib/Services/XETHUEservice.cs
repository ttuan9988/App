using Lib.Entity;
using Lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Services
{
    public class XETHUEservice
    {
        private IXETHUERepository XETHUERepository { get; set; }
        private ApplicationDbContext dbContext;

        public XETHUEservice(ApplicationDbContext dbContext, IXETHUERepository XETHUERepository)
        {
            this.dbContext = dbContext;
            this.XETHUERepository = XETHUERepository;
        }

        public void Save()
        {
            dbContext.SaveChanges();
        }

        public List<XETHUE> GetXETHUEList()
        {
            return XETHUERepository.GetXETHUEList();
        }
        public void InsertXETHUE(XETHUE tk)
        {
            XETHUERepository.Add(tk);
            Save();
        }
        public void DeleteXETHUE(XETHUE tk)
        {
            XETHUE tk1 = dbContext.XETHUE.Where(ad => ad.maxe == tk.maxe).FirstOrDefault();
            XETHUERepository.Delete(tk1);
            Save();
        }
        public void UpdateXETHUE(XETHUE tk)
        {
            XETHUE tk1 = dbContext.XETHUE.Where(ad => ad.maxe == tk.maxe).FirstOrDefault();
            tk1.maxe = tk.maxe;
            tk1.tenxe = tk.tenxe;
            tk1.loaixe = tk.loaixe;
            tk1.biensoxe = tk.biensoxe;
            tk1.giaxe = tk.giaxe;
            tk1.hinhanh = tk.hinhanh;
            tk1.tinhtrang = tk.tinhtrang;
            tk1.maphieu = tk.maphieu;
            tk1.xe = tk.xe;
            tk1.trangthai = tk.trangthai;
            tk1.kiemdinh = tk.kiemdinh;
            tk1.hanthaynhot = tk.hanthaynhot;
            tk1.vitrixe = tk.vitrixe;
            XETHUERepository.Update(tk1);
            Save();
        }
    }
}
