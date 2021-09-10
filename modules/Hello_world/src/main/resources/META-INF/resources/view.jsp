<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="hello_world.caption"/></b>
</p>


<h2>

	<table>
		<tr >

			<th style="width: 200px"> First Name </th>
			<th style="width: 200px"> Last Name </th>
			<th style="width: 100px"> Email </th>

		</tr>

		<c:forEach items="${usersmock}" var="user" >

			<tr>

				<td><c:out value="${user.getFirstName()}" /></td>
				<td><c:out value="${user.getLastName()}" /></td>
				<td><c:out value="${user.getEmail()}" /></td>

			</tr>

		</c:forEach>
	</table>
</h2>
<h2>

	<ul>
		<c:if  test="$role"></c:if>
		<c:forEach var="aq" items="${article}">
			<li>${aq}</li>
		</c:forEach>
	</ul>

</h2>
