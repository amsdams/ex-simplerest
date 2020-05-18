package com.amsdams.ex.simplerest.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduledConfiguration {

	@Autowired
	ProductRepository productRepository;

	@Scheduled(fixedRate = 10000)
	public void executePublish() {
		List<Product> products = productRepository.findAll();
		log.info("p" + products.size());
		products.stream().forEach(p -> log.info(p.toString()));
		for (Product p : products) {

			if (p.getPublishOn() != null && (p.getPublishOn().isBefore(LocalDate.now())|| p.getPublishOn().isEqual(LocalDate.now()))) {
				p.setStatus(Status.PUBLISHED);
				p.setPublishOn(null);
				productRepository.saveAndFlush(p);
			}

			if (p.getUnpublishOn() != null && (p.getUnpublishOn().isBefore(LocalDate.now())|| p.getUnpublishOn().isEqual(LocalDate.now()))) {
				p.setStatus(Status.DRAFT);
				p.setUnpublishOn(null);
				productRepository.saveAndFlush(p);
			}
			
			if (p.getArchiveOn() != null && (p.getArchiveOn().isBefore(LocalDate.now()) || p.getArchiveOn().isEqual(LocalDate.now()))) {
				p.setStatus(Status.ARCHIVED);
				p.setArchiveOn(null);
				productRepository.saveAndFlush(p);
			}

		}

		
		
		List<Product> toUnpublish = productRepository.findAllByUnpublishOn(LocalDate.now());
		log.info("to unpublish" + toUnpublish.size());
		
		List<Product> toArchive = productRepository.findAllByArchiveOn(LocalDate.now());
		log.info("to archive" + toArchive.size());
		
		List<Product> drafts = productRepository.findAllByStatus(Status.DRAFT);
		
		log.info("drafts");
		drafts.stream().forEach(p -> log.info(p.toString()));
		List<Product> published = productRepository.findAllByStatus(Status.PUBLISHED);
		log.info("published");
		
		published.stream().forEach(p -> log.info(p.toString()));
		List<Product> archived = productRepository.findAllByStatus(Status.ARCHIVED);
		log.info("archived");
		archived.stream().forEach(p -> log.info(p.toString()));

		
	}
}
