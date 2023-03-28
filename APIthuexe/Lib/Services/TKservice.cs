using Lib.Entity;
using Lib.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Services
{
    public class TKservice
    {
        private ITKRepository TKRepository { get; set; }
        private ApplicationDbContext dbContext;

        public TKservice(ApplicationDbContext dbContext, ITKRepository TKRepository)
        {
            this.dbContext = dbContext;
            this.TKRepository = TKRepository;
        }
        
        public void Save()
        {
            dbContext.SaveChanges();
        }

        public List<TK> GetTKList()
        {
            return TKRepository.GetTKList();
        }
        public void InsertTK(TK tk)
        {
            TKRepository.Add(tk);
            Save();
        }
        public void DeleteTK(TK tk)
        {
            TK tk1 = dbContext.TK.Where(ad => ad.taikhoan == tk.taikhoan).FirstOrDefault();
            TKRepository.Delete(tk1);
            Save();
        }
        public void UpdateTK(TK tk)
        {
            TK tk1 = dbContext.TK.Where(ad => ad.taikhoan == tk.taikhoan).FirstOrDefault();
            tk1.matkhau=tk.matkhau;
            tk1.quyen=tk.quyen;
            tk1.ten =tk.ten;
            tk1.sdt=tk.sdt;
            tk1.tien=tk.tien;
            tk1.vitri=tk.vitri;
            TKRepository.Update(tk1);
            Save();
        }
    }
}
