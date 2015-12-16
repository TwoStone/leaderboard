package com.github.twostone.leaderboard.ui.view;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.vaadin.viritin.LazyList.EntityProvider;
import org.vaadin.viritin.fields.MTable;

import java.io.Serializable;
import java.util.List;

class PagingRepostioryAdapter<T> implements EntityProvider<T> {

  private static final long serialVersionUID = 1L;
  private PagingAndSortingRepository<T, ? extends Serializable> repository;
  private MTable<T> table;

  PagingRepostioryAdapter(PagingAndSortingRepository<T, ? extends Serializable> repository,
      MTable<T> table) {
    super();
    this.repository = repository;
    this.table = table;
    this.table.lazyLoadFrom(this, this);
  }

  @Override
  public int size() {
    return (int) this.repository.count();
  }

  @Override
  public List<T> findEntities(int firstRow) {
    int page = firstRow / this.table.getPageLength();
    if (this.table.getPageLength() > 0) {
      return this.repository.findAll(new PageRequest(page, this.table.getPageLength()))
          .getContent();
    } else {
      return Lists.newArrayList(this.repository.findAll());
    }
  }
}
