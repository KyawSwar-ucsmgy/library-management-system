package com.spring_security_one.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_security_one.demo.model.Author;
import com.spring_security_one.demo.model.AuthorDto;
import com.spring_security_one.demo.repository.AuthorRepository;


@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	public List<Author> searchAuthors(String keyword) {

		if (keyword != null) {

			return authorRepository.search(keyword);
		} else
			return (List<Author>) authorRepository.findAll();
	}

	public void setAuthorRepository(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public void createAuthor(AuthorDto authorDto) {
		// TODO Auto-generated method stub
		Author author = new Author();
		author.setName(authorDto.getName());
		author.setBio(authorDto.getBio());
		authorRepository.save(author);
	}

	@Override
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}

	@Override
	public void editAuthor(Long id, AuthorDto authorDto) {
		// TODO Auto-generated method stub
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			Author author = authorOptional.get();
			author.setName(authorDto.getName());
			author.setBio(authorDto.getBio());
			authorRepository.save(author);
		}
	}

	@Override
	public void deleteAuthor(Long id) {
		// TODO Auto-generated method stub
		authorRepository.deleteById(id);
	}

}
