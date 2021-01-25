package com.enigma.spotify.spesification;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionJpaSpesification {
    public static Specification<Transaction> findByCriterias(Transaction transaction){
        return new Specification<Transaction>() {
            @Override
            public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final Collection<Predicate> predicates = new ArrayList<>();

                Join<Transaction, Song> join = root.join("item");

                if(transaction!=null){
                    if (!StringUtils.isEmpty(transaction.getAmount())){
                        final Predicate transactionAmountPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("amount"),transaction.getAmount());
                        predicates.add(transactionAmountPredicate);
                    }
                    if (!StringUtils.isEmpty(transaction.getAlbumDiscount())){
                        final Predicate transactionDiscountPredicate = criteriaBuilder.equal(root.get("albumDiscount"),transaction.getAlbumDiscount());
                        predicates.add(transactionDiscountPredicate);
                    }
                    if (!StringUtils.isEmpty(transaction.getSongTitle())){
                        final Predicate transactionSongPredicate = criteriaBuilder.like(criteriaBuilder.lower(join.get("title")),"%"+transaction.getSongTitle().toLowerCase()+"%");
                        predicates.add(transactionSongPredicate);
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
