package com.example.pw3.services;

import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.pw3.models.Categoria;
import com.example.pw3.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Collection<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria findById(Long id) {
        return repository.findById(id).get();
    }

    public Categoria delete(Categoria categoria) {
        Categoria categoriaOld = findById(categoria.getId());
        repository.delete(categoria);
        return categoriaOld;
    }

    public Categoria updateById(Categoria categoria) {
        try {
            Categoria categoriaAtualizada = repository.findById(categoria.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + categoria.getId()));

            // Atualiza os campos da categoria
            categoriaAtualizada.setNome(categoria.getNome());

            return repository.save(categoriaAtualizada);
        } catch (EntityNotFoundException e) {
            return new Categoria();
        }
    }
}
