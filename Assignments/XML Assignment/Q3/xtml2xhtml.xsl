<?xml version="1.0" encoding="utf-8"?>
<!-- Created with Liquid Studio 2018 (https://www.liquid-technologies.com) -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <html>
            <body>
                <h2>Survey Summary</h2>
                1. When did you start playing Pokemon Go?
                <p>
                -July:   
                <xsl:value-of select="count(document/person/month[.='July'])" />
                <br/>
                -May:
                <xsl:value-of select="count(document/person/month[. ='May'])" />
                <br/>
                -August:
                <xsl:value-of select="count(document/person/month[. ='August'])" />
                <br/>
                -June:
                <xsl:value-of select="count(document/person/month[. ='June'])" />
                <br/>
                -September:
                <xsl:value-of select="count(document/person/month[. ='September'])" />
                <br/>
                -December:
                <xsl:value-of select="count(document/person/month[. ='December'])" />
                <br/>
                -November:
                <xsl:value-of select="count(document/person/month[. ='November'])" />
                <br/>
                -January:
                <xsl:value-of select="count(document/person/month[. ='January'])" />
                <br/>
                -February:
                <xsl:value-of select="count(document/person/month[. ='February'])" />
                <br/>
                -March:
                <xsl:value-of select="count(document/person/month[. ='March'])" />
                <br/>
                </p>
                2. What type of phone(OS) do you play on?
                 <p>
                    -Android:
                    <xsl:value-of select="count(document/person/platform[.='Android'])" />
                    <br/>
                    -iOS:
                    <xsl:value-of select="count(document/person/platform[. ='iOS'])" />
                    <br/>
                    -Both:
                    <xsl:value-of select="count(document/person/platform[. ='Both'])" />
                    <br/>
                </p>
                3. What team are you on?
                <p>
                    -Mystic(Blue):
                    <xsl:value-of select="count(document/person/team[.='Mystic (Blue)'])" />
                    <br/>
                    -Valor(Red):
                    <xsl:value-of select="count(document/person/team[. ='Valor (Red)'])" />
                    <br/>
                    -Instinct(Yellow):
                    <xsl:value-of select="count(document/person/team[. ='Instinct (Yellow)'])" />
                    <br/>
                    -None(White):
                    <xsl:value-of select="count(document/person/team[. ='None (White)'])" />
                    <br/>
                </p>
                4. What level are you?
                <p>
                    -Under level 35:
                    <xsl:value-of select="count(document/person/level[number(.) &lt; 35])" />
                    <br/>
                    -Level 35 and above:
                    <xsl:value-of select="count(document/person/level[number(.) &gt; 34])" />
                    <br/>                    
                </p>
                5. How many pokemon have you caught?
                <p>
                    -5001 to 20000:
                    <xsl:value-of select="count(document/person/caught[number(.) &gt; 5000 and number(.)&lt; 20001])" />
                    <br/>
                    -1001 to 5000:
                    <xsl:value-of select="count(document/person/caught[number(.) &gt; 1000 and number(.)&lt; 5001])" />
                    <br/>
                    -More than 20000:
                    <xsl:value-of select="count(document/person/caught[number(.) &gt; 20000])" />
                    <br/>
                    -0 to 1000:
                    <xsl:value-of select="count(document/person/caught[number(.) &gt; 0 and number(.)&lt; 1001])" />
                    <br/>   
                </p>
                6.What colour is your Jogger medal?
                <p>
                    -Gold:
                    <xsl:value-of select="count(document/person/distance[number(.) &gt; 999])" />
                    <br/>
                    -Silver:
                    <xsl:value-of select="count(document/person/distance[number(.) &gt; 99 and number(.)&lt; 1000])" />
                    <br/>
                    -Bronze:
                     <xsl:value-of select="count(document/person/distance[number(.) &gt; 0 and number(.)&lt; 100])" />                   
                    <br/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>