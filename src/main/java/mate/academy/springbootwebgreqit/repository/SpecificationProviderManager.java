package mate.academy.springbootwebgreqit.repository;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String Key);
}
