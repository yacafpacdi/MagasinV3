<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<hr />
<div>
    <h1>Panier</h1>
    <c:if test="${empty panier}">
        <p>Votre panier est vide</p>
    </c:if>
    <c:if test="${not empty panier}">
        <table border="1">
            <thead>
                <tr>
                    <th>Reference</th>
                    <th>Nom</th>
                    <th>quantité</th>
                    <th>prixHt / unité</th>
                    <th>Total Ligne</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${panier}" var="ligne">
                    <tr>
                        <td>${ligne.reference}</td>
                        <td>${ligne.produit.label}</td>
                        <td>${ligne.qte}</td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.produit.prixHT}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.prixHT}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                        <td>
                            <a href="#"> +1 </a> | <a href="#"> -1 </a> | <a href="#"> supprimer </a>
                        </td>
                    </tr>
                </c:forEach>
            <tfoot>
                <tr>
                    <td colspan="5"> Total HT : </td>
                    <td>${total}</td>
                </tr>
            </tfoot>
            </tbody>
        </table>
    </c:if>
</div>
