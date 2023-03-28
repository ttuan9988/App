using Lib.Data;
using Lib.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Repositories
{
    public interface ITKRepository : IRepository<TK>
    {
        List<TK> GetTKList();
    }
    public class TKRepository : RepositoryBase<TK>, ITKRepository
    {
        public TKRepository(ApplicationDbContext dbContext) : base(dbContext)
        {

        }
        public List<TK> GetTKList()
        {
            var query = _dbcontext.TK.AsQueryable();
            return query.ToList();
        }
    }
}

