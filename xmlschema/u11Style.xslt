<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <xsl:apply-templates />
  </body>
  </html>
</xsl:template>

<xsl:template match="abschlussarbeit">
    <xsl:apply-templates />
</xsl:template>

<xsl:template match="abschlussarbeit/zusammenfassung">
<h1>Zusammenfassung in Deutsch</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/abstract">
<h1>Abstract in Englisch</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/einleitung">
	<h1>Einleitung</h1>
	<p><xsl:apply-templates /></p>
</xsl:template>

<xsl:template match="abschlussarbeit/einleitung/motivation">
	<h2>Motivation</h2>
	<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/einleitung/ziele">
	<h2>Ziele der Arbeit</h2>
	<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/arbeiten">
	<h1>Verwandte Arbeiten</h1>
	<p><xsl:apply-templates /></p>
</xsl:template>

<xsl:template match="abschlussarbeit/arbeiten/arbeit">
	<h2>Arbeit</h2>
	<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/grundlagen">
<h1>Grundlagen</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/konzept">
<h1>Konzept</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/realisierung">
<h1>Realisierung</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/implementierung">
<h1>Implementierung</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/beispiel">
<h1>Beispiel</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/szenarien">
<h1>Anwendungsszenarien</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/ausblick">
<h1>Resümee und Ausblick</h1>
<p><xsl:value-of select="."/></p>
</xsl:template>

<xsl:template match="abschlussarbeit/literatur">
<h1>Literaturverzeichnis</h1>
<p><xsl:apply-templates /></p>
</xsl:template>

<xsl:template match="abschlussarbeit/literatur/eintrag">
<h3><xsl:value-of select="@titel" /></h3>
<p>Autor: <xsl:value-of select="@autor" />, Seite: <xsl:value-of select="@seite" />, Verlag: <xsl:value-of select="@verlag" />, Jahr: <xsl:value-of select="@jahr" /></p>
</xsl:template>



</xsl:stylesheet>

