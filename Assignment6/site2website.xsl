<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes" />
    <xsl:template match="site">
        <website>
            <xsl:attribute name="id">
                <xsl:value-of select="@id"/>
            </xsl:attribute>
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            <description>
                <xsl:value-of select="@description"/>
            </description>
            <xsl:apply-templates select="views/view"/>

        </website>
        </xsl:template>

        <xsl:template match="views/view">
            <page>
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
                <xsl:attribute name="name">
                    <xsl:value-of select="@name"/>
                </xsl:attribute>
                <description>
                    <xsl:value-of select="description"/>
                </description>
                <xsl:apply-templates select="component"/>
            </page>
        </xsl:template>

        <xsl:template match="component">
            <widget>
                <xsl:variable name="ref" select="@ref"/>
                <xsl:attribute name="id">
                    <xsl:value-of select="/site/components/component[@id = $ref]/@id"/>
                </xsl:attribute>
                <xsl:variable name="type" select="/site/components/component[@id = $ref]/@type"/>
                <xsl:attribute name="type">
                    <xsl:value-of select="$type"/>
                </xsl:attribute>
                <xsl:choose>
                    <xsl:when test="$type = 'TEXT'">
                        <text>
                            <xsl:value-of select="/site/components/component[@id = $ref]/text"/>
                        </text>
                    </xsl:when>
                    <xsl:when test="$type = 'YOUTUBE'">
                        <xsl:attribute name="url">
                            <xsl:value-of select="/site/components/component[@id = $ref]/@url"/>
                        </xsl:attribute>
                    </xsl:when>
                    <xsl:when test="$type = 'IMG'">
                        <xsl:attribute name="src">
                            <xsl:value-of select="/site/components/component[@id = $ref]/@src"/>
                        </xsl:attribute>
                    </xsl:when>
                    <xsl:when test="$type = 'BUTTON'">
                        <xsl:attribute name="label">
                            <xsl:value-of select="/site/components/component[@id = $ref]/@label"/>
                        </xsl:attribute>
                    </xsl:when>
                </xsl:choose>

            </widget>
        </xsl:template>


</xsl:stylesheet>