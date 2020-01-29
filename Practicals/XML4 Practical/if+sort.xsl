<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<body>
				<h2>My CD Collection</h2>
				<table border="1">
					<tr bgcolor="cyan">
						<th style="text-align:left">Title</th>
						<th style="text-align:left">Artist</th>
						<th style="text-align:left">Country</th>	
						<th style="text-align:left">Company</th>						
						<th style="text-align:left">Price</th>						
						<th style="text-align:left">Year</th>					
					</tr>
					<xsl:for-each select="CATALOG/CD">
						<xsl:sort select="YEAR"/>
						<xsl:if test="COUNTRY = 'UK'">
							<tr>
								<td>
									<xsl:value-of select="TITLE"/>
								</td>
								<td>
									<xsl:value-of select="ARTIST"/>
								</td>
								<td>
									<xsl:value-of select="COUNTRY"/>
								</td>
								<td>
									<xsl:value-of select="COMPANY"/>
								</td>
								<td>
									<xsl:value-of select="PRICE"/>
								</td>
								<td>
									<xsl:value-of select="YEAR"/>		
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>