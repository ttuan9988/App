using Lib.Entity;
using Lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Services
{

    public class PHIEUTHUEXEservice
    {
        private IPHIEUTHUEXERepository PHIEUTHUEXERepository { get; set; }
        private ApplicationDbContext dbContext;

        public PHIEUTHUEXEservice(ApplicationDbContext dbContext, IPHIEUTHUEXERepository PHIEUTHUEXERepository)
        {
            this.dbContext = dbContext;
            this.PHIEUTHUEXERepository = PHIEUTHUEXERepository;
        }

        public void Save()
        {
            dbContext.SaveChanges();
        }

        public List<PHIEUTHUEXE> GetPHIEUTHUEXEList()
        {
            return PHIEUTHUEXERepository.GetPHIEUTHUEXEList();
        }
        public void InsertPHIEUTHUEXE(PHIEUTHUEXE ptx)
        {
            PHIEUTHUEXERepository.Add(ptx);
            Save();
        }
        public void DeletePHIEUTHUEXE(PHIEUTHUEXE ptx)
        {
            PHIEUTHUEXE tk1 = dbContext.PHIEUTHUEXE.Where(ad => ad.maphieu == ptx.maphieu).FirstOrDefault();
            PHIEUTHUEXERepository.Delete(tk1);
            Save();
        }
        public void UpdatePHIEUTHUEXE(PHIEUTHUEXE ptx)
        {
            PHIEUTHUEXE tk1 = dbContext.PHIEUTHUEXE.Where(ad => ad.maphieu == ptx.maphieu).FirstOrDefault();
            tk1.ngaythue = ptx.ngaythue;
            tk1.ngaytra = ptx.ngaytra;
            tk1.tiencoc = ptx.tiencoc;
            tk1.taikhoan = ptx.taikhoan;
            tk1.biensoxe = ptx.biensoxe;
            tk1.banglai = ptx.banglai;
            tk1.duyet = ptx.duyet;
            tk1.thoigian = ptx.thoigian;
            tk1.tienxe = ptx.tienxe;
            tk1.xem = ptx.xem;
            PHIEUTHUEXERepository.Update(tk1);
            Save();
        }
    }
}
