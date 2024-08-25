package com.bkartisan.be.Repository;

import java.util.ArrayList;
import java.util.List;

import com.bkartisan.be.Constant.OrderConstants;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductsByFilters(ProductFilterForAdminPageDTO filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.product() != null) {
            predicates.add(cb.like(product.get("name"), "%" + filters.product() + "%"));
        }
        if (filters.seller() != null) {
            predicates.add(cb.equal(product.get("seller"), filters.seller()));
        }
        if (filters.collab() != null) {
            predicates.add(cb.equal(product.get("approver"), filters.collab()));
        }
        if (filters.startPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), filters.startPrice()));
        }
        if (filters.endPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), filters.endPrice()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        // Handle ordering
        if (filters.orders() != null) {
            switch (filters.orders()) {
                case OrderConstants.NEW_TO_OLD:
                    query.orderBy(cb.desc(product.get("approvedAt")));
                    break;
                case OrderConstants.OLD_TO_NEW:
                    query.orderBy(cb.asc(product.get("approvedAt")));
                    break;
                case OrderConstants.LOW_TO_HIGH:
                    query.orderBy(cb.asc(product.get("price")));
                    break;
                default:
                    query.orderBy(cb.desc(product.get("price")));
                    break;
            }
        }

        TypedQuery<Product> typedQuery = entityManager.createQuery(query);

        // Handle pagination
        if (filters.offset() != null && filters.page() != null) {
            int offset = Integer.parseInt(filters.offset().toString());
            int page = Integer.parseInt(filters.page().toString());
            typedQuery.setFirstResult(offset * (page - 1));
            typedQuery.setMaxResults(offset);
        } else {
            typedQuery.setFirstResult(0);
            typedQuery.setMaxResults(10);
        }

        return typedQuery.getResultList();
    }
}