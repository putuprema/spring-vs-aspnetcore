using Microsoft.EntityFrameworkCore;

namespace Eater.Interfaces
{
    public interface IBaseRepository<T> where T : class
    {
        public void Delete(T entity);
        public ValueTask<T?> GetById(object id);
        public Task<List<T>> GetAll();
        public T Save(T entity);
        public T Update(T entity);
        public Task SaveChangesAsync();
        public DbSet<T> Set();
    }
}
